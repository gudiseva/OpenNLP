package com.NLPComponent;

import java.io.IOException;

public class Main {
	/*
	 * This is the comment
	 */
	public static void main(String[] args)
	{
		TextProcessor nlpTask = new TextProcessor();
		//String paragraph = " Advik Rana is a software Professional. Apache openNLP supports the most common NLP tasks, such as tokenization, sentence segmentation, part-of-speech tagging, named entity extraction, chunking, parsing, and coreference resolution. These tasks are usually required to build more advanced text processing services. OpenNLP also includes maximum entropy and perceptron based machine learning.";
		String paragraph = " Stephen Roberts is a software Professional. Apache openNLP supports the most common NLP tasks, such as tokenization, sentence segmentation, part-of-speech tagging, named entity extraction, chunking, parsing, and coreference resolution. These tasks are usually required to build more advanced text processing services. OpenNLP also includes maximum entropy and perceptron based machine learning.";
		String kanadaLang = "ಒತ್ತುವುದರ ಮೂಲಕ ಈಗ ನೇರವಾಗಿ ಕನ್ನಡದಲ್ಲಿ ಬರೆಯಬಹುದು! ವಿವರಗಳಿಗೆ ಈ ಪುಟ ನೋಡಿ.";
		
		System.out.println(">>> Sentence Tokens: <<<");
		nlpTask.SentenceTokenizer(paragraph);
		
		try {
			System.out.println(">>> POS Tagging: <<<");
			nlpTask.POSTag(paragraph);
			System.out.println(">>> Name Finder: <<<");
			nlpTask.findName(paragraph);
			System.out.println(">>> Paragraph Language Detection: <<<");
			nlpTask.LanguageDetection(paragraph);
			System.out.println(">>> Kannada Language Detection: <<<");
			nlpTask.LanguageDetection(kanadaLang);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}