package com.mns.uima.internal;

import com.mnsuk.uima.NLU;


public class TesterExample {
	public static void main(String[] args) {
		String CASFile1 = "/Users/martin/Documents/Development/Java/Annotators/NLU/data/nlu_fr.xmi";  // _InitialView
		String fileType1 = "/Users/martin/Documents/Development/Java/Annotators/NLU/desc/debug_fr-ts.xml";  // _InitialView

		NLU anno = new NLU();
		anno.setPUsername("uuuuuu");
		anno.setPPassword("ppppppp");

		String todo = "d";   // c = custom, d = default, b = both
		
		//		Custom
		if (todo.equalsIgnoreCase("C")) {
			anno.setPCustomEnable(true);
			anno.setPModel("mmmmmm");
			anno.setPDocumentEnable(false);
			anno.setPDocumentSentiment(false);
			anno.setPDocumentEmotion(false);
			anno.setPEntityEnable(false);
			anno.setPEntitySentiment(false);
			anno.setPEntityEmotion(false);
		}
		
		//		Default
		if (todo.equalsIgnoreCase("D")) {
			anno.setPCustomEnable(false);
			anno.setPModel("none");
			anno.setPDocumentEnable(true);
			anno.setPDocumentSentiment(true);
			anno.setPDocumentEmotion(false);
			anno.setPEntityEnable(true);
			anno.setPEntitySentiment(true);
			anno.setPEntityEmotion(false);
		}
		
		//		Both
		if (todo.equalsIgnoreCase("B")) {
			anno.setPCustomEnable(true);
			anno.setPModel("mmmmmm");
			anno.setPDocumentEnable(true);
			anno.setPDocumentSentiment(true);
			anno.setPDocumentEmotion(false);
			anno.setPEntityEnable(true);
			anno.setPEntitySentiment(false);
			anno.setPEntityEmotion(false);
		}
		anno.TestAnnotator( fileType1, CASFile1);

	}
}
