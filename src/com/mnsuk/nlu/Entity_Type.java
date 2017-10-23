
/* First created by JCasGen Wed Apr 05 13:29:03 BST 2017 */
package com.mnsuk.nlu;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Mon Oct 23 18:03:32 BST 2017
 * @generated */
public class Entity_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Entity.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("com.mnsuk.nlu.Entity");
 
  /** @generated */
  final Feature casFeat_entityType;
  /** @generated */
  final int     casFeatCode_entityType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getEntityType(int addr) {
        if (featOkTst && casFeat_entityType == null)
      jcas.throwFeatMissing("entityType", "com.mnsuk.nlu.Entity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_entityType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEntityType(int addr, String v) {
        if (featOkTst && casFeat_entityType == null)
      jcas.throwFeatMissing("entityType", "com.mnsuk.nlu.Entity");
    ll_cas.ll_setStringValue(addr, casFeatCode_entityType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentiment;
  /** @generated */
  final int     casFeatCode_sentiment;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSentiment(int addr) {
        if (featOkTst && casFeat_sentiment == null)
      jcas.throwFeatMissing("sentiment", "com.mnsuk.nlu.Entity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_sentiment);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentiment(int addr, String v) {
        if (featOkTst && casFeat_sentiment == null)
      jcas.throwFeatMissing("sentiment", "com.mnsuk.nlu.Entity");
    ll_cas.ll_setStringValue(addr, casFeatCode_sentiment, v);}
    
  
 
  /** @generated */
  final Feature casFeat_anger;
  /** @generated */
  final int     casFeatCode_anger;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAnger(int addr) {
        if (featOkTst && casFeat_anger == null)
      jcas.throwFeatMissing("anger", "com.mnsuk.nlu.Entity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_anger);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnger(int addr, String v) {
        if (featOkTst && casFeat_anger == null)
      jcas.throwFeatMissing("anger", "com.mnsuk.nlu.Entity");
    ll_cas.ll_setStringValue(addr, casFeatCode_anger, v);}
    
  
 
  /** @generated */
  final Feature casFeat_disgust;
  /** @generated */
  final int     casFeatCode_disgust;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDisgust(int addr) {
        if (featOkTst && casFeat_disgust == null)
      jcas.throwFeatMissing("disgust", "com.mnsuk.nlu.Entity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_disgust);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDisgust(int addr, String v) {
        if (featOkTst && casFeat_disgust == null)
      jcas.throwFeatMissing("disgust", "com.mnsuk.nlu.Entity");
    ll_cas.ll_setStringValue(addr, casFeatCode_disgust, v);}
    
  
 
  /** @generated */
  final Feature casFeat_fear;
  /** @generated */
  final int     casFeatCode_fear;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getFear(int addr) {
        if (featOkTst && casFeat_fear == null)
      jcas.throwFeatMissing("fear", "com.mnsuk.nlu.Entity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_fear);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFear(int addr, String v) {
        if (featOkTst && casFeat_fear == null)
      jcas.throwFeatMissing("fear", "com.mnsuk.nlu.Entity");
    ll_cas.ll_setStringValue(addr, casFeatCode_fear, v);}
    
  
 
  /** @generated */
  final Feature casFeat_joy;
  /** @generated */
  final int     casFeatCode_joy;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getJoy(int addr) {
        if (featOkTst && casFeat_joy == null)
      jcas.throwFeatMissing("joy", "com.mnsuk.nlu.Entity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_joy);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setJoy(int addr, String v) {
        if (featOkTst && casFeat_joy == null)
      jcas.throwFeatMissing("joy", "com.mnsuk.nlu.Entity");
    ll_cas.ll_setStringValue(addr, casFeatCode_joy, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sadness;
  /** @generated */
  final int     casFeatCode_sadness;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSadness(int addr) {
        if (featOkTst && casFeat_sadness == null)
      jcas.throwFeatMissing("sadness", "com.mnsuk.nlu.Entity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_sadness);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSadness(int addr, String v) {
        if (featOkTst && casFeat_sadness == null)
      jcas.throwFeatMissing("sadness", "com.mnsuk.nlu.Entity");
    ll_cas.ll_setStringValue(addr, casFeatCode_sadness, v);}
    
  
 
  /** @generated */
  final Feature casFeat_source;
  /** @generated */
  final int     casFeatCode_source;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSource(int addr) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "com.mnsuk.nlu.Entity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_source);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSource(int addr, String v) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "com.mnsuk.nlu.Entity");
    ll_cas.ll_setStringValue(addr, casFeatCode_source, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Entity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_entityType = jcas.getRequiredFeatureDE(casType, "entityType", "uima.cas.String", featOkTst);
    casFeatCode_entityType  = (null == casFeat_entityType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_entityType).getCode();

 
    casFeat_sentiment = jcas.getRequiredFeatureDE(casType, "sentiment", "uima.cas.String", featOkTst);
    casFeatCode_sentiment  = (null == casFeat_sentiment) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentiment).getCode();

 
    casFeat_anger = jcas.getRequiredFeatureDE(casType, "anger", "uima.cas.String", featOkTst);
    casFeatCode_anger  = (null == casFeat_anger) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_anger).getCode();

 
    casFeat_disgust = jcas.getRequiredFeatureDE(casType, "disgust", "uima.cas.String", featOkTst);
    casFeatCode_disgust  = (null == casFeat_disgust) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_disgust).getCode();

 
    casFeat_fear = jcas.getRequiredFeatureDE(casType, "fear", "uima.cas.String", featOkTst);
    casFeatCode_fear  = (null == casFeat_fear) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fear).getCode();

 
    casFeat_joy = jcas.getRequiredFeatureDE(casType, "joy", "uima.cas.String", featOkTst);
    casFeatCode_joy  = (null == casFeat_joy) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_joy).getCode();

 
    casFeat_sadness = jcas.getRequiredFeatureDE(casType, "sadness", "uima.cas.String", featOkTst);
    casFeatCode_sadness  = (null == casFeat_sadness) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sadness).getCode();

 
    casFeat_source = jcas.getRequiredFeatureDE(casType, "source", "uima.cas.String", featOkTst);
    casFeatCode_source  = (null == casFeat_source) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_source).getCode();

  }
}



    