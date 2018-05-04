package com.NLPComponent;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderFactory;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class CustomClassifierTrainer {
	static String onlpModelPath = "src//models//en-ner-indianperson.bin";
    // training data set
    static String trainingDataFilePath = "src//training//indianperson.txt";
	
	public static void main(String[] args) throws IOException{
		
		InputStreamFactory isf = new InputStreamFactory() {
            public InputStream createInputStream() throws IOException {
                return new FileInputStream(trainingDataFilePath);
            }
        };
        
        Charset charset = Charset.forName("UTF-8");
        ObjectStream<String> lineStream = new PlainTextByLineStream(isf, charset);
        ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);
        
        TokenNameFinderModel model;
        TokenNameFinderFactory nameFinderFactory = new TokenNameFinderFactory();

        try {
            model = NameFinderME.train("en", "person", sampleStream, TrainingParameters.defaultParams(),
                    nameFinderFactory);
        } finally {
            sampleStream.close();
        }
        
        BufferedOutputStream modelOut = null;
        try {
                        modelOut = new BufferedOutputStream(new FileOutputStream(onlpModelPath));
                        model.serialize(modelOut);
        } finally {
                        if (modelOut != null)
                                        modelOut.close();
        }
	}
}