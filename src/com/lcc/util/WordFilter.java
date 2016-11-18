package com.lcc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordFilter {

	public static void readType(Map<String, String> filtermap) {
		InputStream inputStream = WordFilter.class.getClassLoader().getSystemResourceAsStream("filter.dic");
		String text = null;
		String key = null;
		String value = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		try {
			while ((text = br.readLine()) != null) {
				key = text.substring(0, 2);
				value = text.substring(3);
				filtermap.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void readType(Map<String, String> flitermap, String url) {
		String text = null;
		String key = null;
		String value = null;
		File file = new File(url);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((text = br.readLine()) != null) {
				System.out.println(text);
				key = text.substring(0, 2);
				System.out.println(key);
				value = text.substring(3);
				System.out.println(value);
				flitermap.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean  match( String type, String text  ){
		 String  regex =".*"+"("+type+")"+".*";
		 Pattern pattern = Pattern.compile(regex); 
	     Matcher matcher = pattern.matcher(text);
	     return matcher.matches() ;
	}
	
	public static void filterword( String keyword , Map<String , String > filtermap, Map<String, String > keymap )	{
		for( String key : filtermap.keySet() ){
			if( match( keyword, filtermap.get(key))){
				keymap.put(key, keyword);
				break;
			}
		}
	}
	
}
