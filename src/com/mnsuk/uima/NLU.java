package com.mnsuk.uima;

import java.io.FileInputStream;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
// import org.apache.uima.cas.CASException;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;
import org.apache.uima.util.XMLInputSource;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.DocumentEmotionResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.DocumentSentimentResults;
// import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionScores;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.FeatureSentimentResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ListModelsResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Model;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentResult;
import com.mns.uima.utils.CASUtils;
import com.mns.uima.utils.DocumentDetails;
import com.mnsuk.nlu.Document;
import com.mnsuk.nlu.Entity;

/**
 * Annotator for Watson Natural Language Understanding
 * <p>
 * @author      martin.saunders@uk.ibm.com
 * @version     1.0.0             
 */
public class NLU extends JCasAnnotator_ImplBase {

	// AE parameters
	private static final String PARAM_ENTITY = "enable_default_entities";
	private static final String PARAM_CUSTOM = "enable_custom_entities";
	private static final String PARAM_DOCUMENT = "enable_default_documentlevel";
	private static final String PARAM_GROUP_CREDENTIALS = "Credentials";
	private static final String PARAM_USERNAME = "username";
	private static final String PARAM_PASSWORD = "password";
	private static final String PARAM_GROUP_ENTITIES = "Entities";	
	private static final String PARAM_ENTITY_SENTIMENT = "entity_sentiment";
	private static final String PARAM_ENTITY_EMOTION = "entity_emotion";
	private static final String PARAM_GROUP_DOCUMENT = "Document";	
	private static final String PARAM_DOCUMENT_SENTIMENT = "document_sentiment";
	private static final String PARAM_DOCUMENT_EMOTION = "document_emotion";
	private static final String PARAM_GROUP_CUSTOM = "Custom";
	private static final String PARAM_MODEL = "custom_model_id";

	// Global Variables
	private Logger logger = null;
	private JCas jcas = null;
	private String text;

	// Global parameters
	private static String pUsername;   
	private static String pPassword;   
	private static String pModel; 
	private static Boolean pEntityEnable;
	private static Boolean pEntitySentiment;
	private static Boolean pEntityEmotion;
	private static Boolean pDocumentEnable;
	private static Boolean pDocumentSentiment;
	private static Boolean pDocumentEmotion;
	private static Boolean pCustomEnable;
	private static Boolean pDefaultEnable;

	/**
	 * Read configuration and initialise annotator with configuration.
	 * <p>
	 *
	 * @param  aContext 
	 * @throws ResourceInitializationException
	 */
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		logger = aContext.getLogger();	
		logger.log(Level.FINE, "NLU: initializing:");
		pUsername = CASUtils.getConfigurationStringValue(aContext, PARAM_GROUP_CREDENTIALS, PARAM_USERNAME);
		pPassword = CASUtils.getConfigurationStringValue(aContext, PARAM_GROUP_CREDENTIALS, PARAM_PASSWORD);	
		pEntityEnable = CASUtils.getConfigurationBooleanValue(aContext, null, PARAM_ENTITY);
		pDocumentEnable = CASUtils.getConfigurationBooleanValue(aContext, null, PARAM_DOCUMENT);
		pCustomEnable = CASUtils.getConfigurationBooleanValue(aContext, null, PARAM_CUSTOM);
		pEntitySentiment = CASUtils.getConfigurationBooleanValue(aContext, PARAM_GROUP_ENTITIES, PARAM_ENTITY_SENTIMENT);
		pEntityEmotion = CASUtils.getConfigurationBooleanValue(aContext, PARAM_GROUP_ENTITIES, PARAM_ENTITY_EMOTION);	
		pDocumentSentiment = CASUtils.getConfigurationBooleanValue(aContext, PARAM_GROUP_DOCUMENT, PARAM_DOCUMENT_SENTIMENT);
		pDocumentEmotion = CASUtils.getConfigurationBooleanValue(aContext, PARAM_GROUP_DOCUMENT, PARAM_DOCUMENT_EMOTION);
		pModel = CASUtils.getConfigurationStringValue(aContext, PARAM_GROUP_CUSTOM, PARAM_MODEL);
		pDefaultEnable = pEntityEnable || pDocumentEnable ? true : false;
		if (( !pDefaultEnable && !pCustomEnable) || (pDocumentEnable && !pDocumentSentiment && !pDocumentEmotion)) {
			throw new ResourceInitializationException("Incompatible boolean feature options selected.", new Object[] {});
		}

		// validate any custom model
		if (pCustomEnable) {
			NaturalLanguageUnderstanding nlu = new NaturalLanguageUnderstanding(NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27, pUsername, pPassword);
			ListModelsResults models = nlu
					.getModels()
					.execute();		
			List<Model> modelList = models.getModels();
			Gson gson = new Gson();
			boolean validModel = false;
			for (Model m : modelList) {
				NLUModel model = gson.fromJson(m.toString(), NLUModel.class); 
				if (model.verify(pModel)) {
					validModel = true;
					break;
				}
			}
			if (!validModel)
				throw new ResourceInitializationException("Custom model specified not found.", new Object[] {}); 
		} 
	}

	/** 
	 * @see org.apache.uima.analysis_component.JCasAnnotator_ImplBase#process(org.apache.uima.jcas.JCas)
	 */

	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		logger.log(Level.FINE, "NLU: processing:");
		this.jcas=aJCas;
		this.text=jcas.getDocumentText();	
		DocumentDetails.extractDocumentDetails(jcas);
		String language = DocumentDetails.language;
		if (language == null || language.isEmpty())
			language = "en";

		NaturalLanguageUnderstanding nlu = new NaturalLanguageUnderstanding(NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27, pUsername, pPassword);
		// ConceptsOptions concepts = new ConceptsOptions.Builder().limit(5).build();

		if (pDefaultEnable) {
			Features standardFeatures = constructAnalysisFeatures(pEntityEnable, pEntitySentiment, pEntityEmotion, 
					pDocumentEnable, pDocumentSentiment, pDocumentEmotion, false, null);
			AnalyzeOptions standardParameters = new AnalyzeOptions.Builder()
					.text(text)
					.features(standardFeatures)
					.returnAnalyzedText(false)
					.language(language)
					.build();

			AnalysisResults standardResults = nlu.analyze(standardParameters).execute();
			/* 
			 * Create single UIMA annotation from NLU Document level annotations.
			 * 
			 * Ideally this should be a feature structure but these aren't usable in CA Studio
			 * so create an annotation over a section of text instead. 
			 */
			if (pDocumentEnable) {
				int end = text.length() > 256 ? 256 : text.length();
				Document doc = new Document(jcas, 0, end);
				if (pDocumentEmotion) {
					EmotionResult emotionResult = standardResults.getEmotion();
					if (emotionResult != null) {
						DocumentEmotionResults dem = emotionResult.getDocument();
						if (dem != null) {
							doc.setAnger(dem.getEmotion().getAnger().toString());
							doc.setDisgust(dem.getEmotion().getDisgust().toString());
							doc.setFear(dem.getEmotion().getFear().toString());
							doc.setJoy(dem.getEmotion().getJoy().toString());
							doc.setSadness(dem.getEmotion().getSadness().toString());
						}
					}
				}
				if (pDocumentSentiment) {
					SentimentResult sentimentResult = standardResults.getSentiment();
					if (sentimentResult != null) {
						DocumentSentimentResults dsr = sentimentResult.getDocument();
						if (dsr != null) {
							doc.setSentiment(dsr.getScore().toString());
						}
					}
				}
				jcas.addFsToIndexes(doc);
				logger.log(Level.INFO, "NLU: processed document anno: " + doc.toString());
			}
			// Create UIMA annotations from NLU Entities
			if (pEntityEnable) {
				List<EntitiesResult> er = standardResults.getEntities();
				for (EntitiesResult res : er) {
					int count = res.getCount();
					int offset = 0;
					int len = res.getText().length();
					FeatureSentimentResults fsr = null;
					EmotionScores es = null;

					for (int i=0; i<count; i++) {
						int start = text.indexOf(res.getText(), offset);
						if ( start >= 0) {
							int end = start + len;
							Entity ent = new Entity(jcas,start, end);
							ent.setSource("default");
							ent.setEntityType(res.getType());
							if (pEntitySentiment) {
								fsr = res.getSentiment();
								if (fsr != null) {
									ent.setSentiment(fsr.getScore().toString());
								}	
							}
							if (pEntityEmotion) {
								es = res.getEmotion();
								if ( es != null) {
									ent.setAnger(es.getAnger().toString());
									ent.setDisgust(es.getDisgust().toString());
									ent.setFear(es.getFear().toString());
									ent.setJoy(es.getJoy().toString());
									ent.setSadness(es.getSadness().toString());
								}
							}
							jcas.addFsToIndexes(ent);
							logger.log(Level.INFO, "NLU: processed anno: " + ent.getCoveredText() + " XXX "+ ent.toString());
						}

					}		
				}
			}
		}

		if (pCustomEnable) {
			Features customFeatures = constructAnalysisFeatures(false, false, false, 
					false, false, false, pCustomEnable, pModel);
			AnalyzeOptions 	customParameters = new AnalyzeOptions.Builder()
					.text(text)
					.features(customFeatures)
					.returnAnalyzedText(false)
					.language(language)
					.build();

			AnalysisResults customResults = nlu.analyze(customParameters).execute();;

			List<EntitiesResult> er = customResults.getEntities();
			for (EntitiesResult res : er) {
				int count = res.getCount();
				int offset = 0;
				int len = res.getText().length();

				for (int i=0; i<count; i++) {
					int start = text.indexOf(res.getText(), offset);
					if ( start >= 0) {
						int end = start + len;
						Entity ent = new Entity(jcas,start, end);
						ent.setSource(pModel);
						ent.setEntityType(res.getType());
						jcas.addFsToIndexes(ent);
						logger.log(Level.INFO, "NLU: processed custom anno: " + ent.getCoveredText() + " XXX "+ ent.toString());
					}

				}		
			}
		}

		logger.log(Level.INFO, "NLU: processed document: " + DocumentDetails.title + " in " + DocumentDetails.language);

	}

	/**
	 * Construct the analysis features based on the options configured
	 * by the user..
	 * <p>
	 *
	 * @param  enable booleans of options selected
	 */
	private Features constructAnalysisFeatures(boolean entityEnable, boolean entitySentiment, boolean entityEmotion, 
			boolean documentEnable, boolean documentSentiment, boolean documentEmotion, boolean customEnable, String model) {	
		EmotionOptions emotion = null;
		SentimentOptions sentiment = null;
		EntitiesOptions entities = null;
		if (documentEnable) {	
			if (documentEmotion) {
				emotion = new EmotionOptions.Builder().build();
			}
			if (documentSentiment) {
				sentiment = new SentimentOptions.Builder()
						.document(true)
						.build();
			}
		}
		if (entityEnable && !customEnable) {
			entities = new EntitiesOptions.Builder()
					.limit(20)
					.sentiment(entitySentiment)
					.emotion(entityEmotion)
					.build();
		}
		if (entityEnable && customEnable) {
			entities = new EntitiesOptions.Builder()
					.limit(20)
					.sentiment(entitySentiment)
					.emotion(entityEmotion)
					.model(model)
					.build();
		}
		if (!entityEnable && customEnable) {
			entities = new EntitiesOptions.Builder()
					.limit(20)
					.model(model)
					.build();
		}


		Features features = null;

		if (entityEnable && documentEnable || customEnable && documentEnable) {
			if (documentSentiment && documentEmotion) {
				features = new Features.Builder().emotion(emotion).sentiment(sentiment).entities(entities).build();
			} else if (documentSentiment) {
				features = new Features.Builder().sentiment(sentiment).entities(entities).build();
			} else if (documentEmotion) {
				features = new Features.Builder().emotion(emotion).entities(entities).build();
			} else {
				features = new Features.Builder().entities(entities).build();
			}
		} else if (entityEnable || customEnable) {
			features = new Features.Builder().entities(entities).build();			
		} else if (documentEnable) {
			if (documentSentiment && documentEmotion) {
				features = new Features.Builder().emotion(emotion).sentiment(sentiment).build();
			} else if (documentSentiment) {
				features = new Features.Builder().sentiment(sentiment).build();
			} else if (documentEmotion) {
				features = new Features.Builder().emotion(emotion).build();
			} 	
		}
		return features;
	}




	/**
	 * Test annotators process method outside of a UIMA pipeline. 
	 * Primary usage is debugging with eclipse.
	 * <p>
	 * Necessary xml and xmi files may be generated in ICA Studio using
	 * the "Save as XMI" feature after annotating a document.
	 *
	 * @param  typeFile xml typesytem file
	 * @param  casFile xmi of exported cas
	 */
	public void TestAnnotator(String typeFile, String casFile) {

		try {
			logger = UIMAFramework.getLogger(NLU.class);

			XMLInputSource xmlIn = new XMLInputSource(typeFile);
			TypeSystemDescription tsDesc = UIMAFramework.getXMLParser().parseTypeSystemDescription(xmlIn);
			CAS cas = CasCreationUtils.createCas( tsDesc, null, null ); 
			XmiCasDeserializer.deserialize(new FileInputStream(casFile), cas, false);
			// and process
			jcas = cas.getJCas();
			pDefaultEnable = pEntityEnable || pDocumentEnable ? true : false;
			if (( !pDefaultEnable && !pCustomEnable) || (pDocumentEnable && !pDocumentSentiment && !pDocumentEmotion)) {
				throw new ResourceInitializationException("Incompatible boolean feature options selected.", new Object[] {});
			}
			process(jcas);
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Error initialising the JCas: " + e.toString(), e);
		}
	}

	/**
	 * Setter for AE parameters used in debugging.
	 * <p>
	 * @param pUsername
	 */
	public static void setPUsername(String pUsername) {
		NLU.pUsername = pUsername;
	}

	/**
	 * Setter for AE parameters used in debugging.
	 * <p>
	 * @param pUsername
	 */
	public static void setPPassword(String pPassword) {
		NLU.pPassword = pPassword;
	}

	/**
	 * Setter for AE parameters used in debugging.
	 * <p>
	 * @param pDocumentEnable
	 */
	public static void setPDocumentEnable(boolean pDocumentEnable) {
		NLU.pDocumentEnable = pDocumentEnable;
	}

	/**
	 * Setter for AE parameters used in debugging.
	 * <p>
	 * @param pDocumentSentiment
	 */
	public static void setPDocumentSentiment(boolean pDocumentSentiment) {
		NLU.pDocumentSentiment = pDocumentSentiment;
	}

	/**
	 * Setter for AE parameters used in debugging.
	 * <p>
	 * @param pDocumentEmotion
	 */
	public static void setPDocumentEmotion(boolean pDocumentEmotion) {
		NLU.pDocumentEmotion = pDocumentEmotion;
	}

	/**
	 * Setter for AE parameters used in debugging.
	 * <p>
	 * @param pEntityEnable
	 */
	public static void setPEntityEnable(boolean pEntityEnable) {
		NLU.pEntityEnable = pEntityEnable;
	}

	/**
	 * Setter for AE parameters used in debugging.
	 * <p>
	 * @param pEntitySentiment
	 */
	public static void setPEntitySentiment(boolean pEntitySentiment) {
		NLU.pEntitySentiment = pEntitySentiment;
	}

	/**
	 * Setter for AE parameters used in debugging.
	 * <p>
	 * @param pEntityEmotion
	 */
	public static void setPEntityEmotion(boolean pEntityEmotion) {
		NLU.pEntityEmotion = pEntityEmotion;
	}

	/**
	 * Setter for AE parameters used in debugging.
	 * <p>
	 * @param pCustom
	 */
	public static void setPCustomEnable(boolean pCustomEnable) {
		NLU.pCustomEnable = pCustomEnable;
	}
	
	/**
	 * Setter for AE parameters used in debugging.
	 * <p>
	 * @param pModel
	 */
	public static void setPModel(String pModel) {
		NLU.pModel = pModel;
	}

	class NLUModel {
		private String status;
		private String model_id;
		private String description;
		public String toString() {
			return "status: " + status + " model_id: " + model_id + " description: " + description;
		}
		public boolean verify(String model) {
			return model.equals(this.model_id) ? true : false;
		}
	};

}
