package com.NLPComponent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.langdetect.Language;
import opennlp.tools.langdetect.LanguageDetector;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

public class TextProcessor {

	public void SentenceTokenizer(String paragraph)
	{
		try (InputStream modelIn = new FileInputStream("src//models/en-sent.bin")) {

			SentenceModel model = new SentenceModel(modelIn);
			SentenceDetectorME sdetector = new SentenceDetectorME(model);
			String sentences[] = sdetector.sentDetect(paragraph);

			// print the sentences detected, to console
			for(int i=0;i<sentences.length;i++){
				System.out.println(sentences[i]);
			}
			modelIn.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void POSTag(String sentence) throws IOException {
		try (InputStream modelIn = new FileInputStream("src//models/en-pos-maxent.bin")) {
			POSModel model = new POSModel(modelIn);
			//Instantiating POSTaggerME class 
			POSTaggerME tagger = new POSTaggerME(model);
			//Tokenizing the sentence using WhitespaceTokenizer class  
			WhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE; 
			String[] tokens = whitespaceTokenizer.tokenize(sentence); 

			String[] tags = tagger.tag(tokens);

			//Instantiating the POSSample class 
			POSSample sample = new POSSample(tokens, tags); 
			System.out.println(sample.toString()); 

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void findName(String sentence) throws IOException {

		//InputStream is = new FileInputStream("src//models//en-ner-indianperson.bin");
		InputStream is = new FileInputStream("src//models//en-ner-person.bin");
		
		// load the model from file
		TokenNameFinderModel model = new TokenNameFinderModel(is);
		is.close();

		// feed the model to name finder class
		NameFinderME nameFinder = new NameFinderME(model);

		String[] sen = sentence.split(" ");
		Span nameSpans[] = nameFinder.find(sen);

		// nameSpans contain all the possible entities detected
		for(Span s: nameSpans){
			System.out.print(s.toString());
			System.out.print("  :  ");
			// s.getStart() : contains the start index of possible name in the input string array
			// s.getEnd() : contains the end index of the possible name in the input string array
			for(int index=s.getStart();index<s.getEnd();index++){
				System.out.print(sen[index]+" ");
			}
			System.out.println("");
		}
			
		System.out.println("End of Name finder");
	}
	
	public void LanguageDetection(String sentence) throws FileNotFoundException
	{
		InputStream is = new FileInputStream("src//models//langdetect-183.bin");
		try {
			LanguageDetectorModel m = new LanguageDetectorModel(is);
			LanguageDetector myCategorizer = new LanguageDetectorME(m);
			
			Language bestLanguage = myCategorizer.predictLanguage(sentence);
			System.out.println("Best language: " + bestLanguage.getLang());
			System.out.println("Best language confidence: " + bestLanguage.getConfidence());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}