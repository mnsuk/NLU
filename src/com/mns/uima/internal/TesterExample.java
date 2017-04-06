package com.mns.uima.internal;

import com.mnsuk.uima.NLU;


public class TesterExample {
	public static void main(String[] args) {
		String CASFile1 = "/Users/martin/Documents/Development/Java/Annotators/NLU/data/nlu_fr.xmi";  // _InitialView
		String fileType1 = "/Users/martin/Documents/Development/Java/Annotators/NLU/desc/debug_fr-ts.xml";  // _InitialView

		NLU anno = new NLU();
		anno.setPUsername("xxxxxx");
		anno.setPPassword("yyyyy");
		anno.setPModel("x");
		anno.setPDocumentEnable(false);
		anno.setPDocumentSentiment(true);
		anno.setPDocumentEmotion(true);
		anno.setPEntityEnable(true);
		anno.setPEntitySentiment(true);
		anno.setPEntityEmotion(true);
		anno.TestAnnotator( fileType1, CASFile1);

	}
}
