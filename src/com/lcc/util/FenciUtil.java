package com.lcc.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class FenciUtil {

	public static void fenci(String text,List<String> keylist) throws IOException{
		String keyword = null;
		
		Analyzer anal = new IKAnalyzer(true);
		StringReader reader=new StringReader(text);
		TokenStream ts=anal.tokenStream("", reader);  
        CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);
        while(ts.incrementToken()){
        	keyword = ""+term.toString();
        	keylist.add(keyword);
        }
        
        reader.close();
	}
}
