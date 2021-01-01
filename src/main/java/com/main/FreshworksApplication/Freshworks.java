package com.main.FreshworksApplication;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Freshworks {
	public static String filename = "D:\\new.txt";

	public static ConcurrentHashMap<String, JSONObject> map = new ConcurrentHashMap<String, JSONObject>();

	public static String content = "";
	  	
	public static Scanner s = new Scanner(System.in);
	
	public static String getDefaultFilePath() {
		return filename;
	}
	
	public static void init(String fn) {
		
		try {
		  	  	content = new String(Files.readAllBytes(Paths.get(fn)));
		  	  		
		  	  	if(content.length()>0) {
		  	  			 
			  	  	ObjectMapper mapper = new ObjectMapper();
				  	  	 
					try {
					     	map = mapper.readValue(content, new TypeReference<ConcurrentHashMap<String, JSONObject>>(){});
					
					} catch (IOException e) {
					        e.printStackTrace();
					}		
		  	  	}
		} catch (IOException e) {
            e.printStackTrace();
        }	
	}
	
	public static void init() {
		init(filename);
	}
	
	public static String createJSON(String fn, String mapKey, JSONObject json) throws CustomException {
		
		if(mapKey.length() > 32)
			throw new CustomException("Error: Key length should not exceed 32\nCreate Unsuccessfull");
		
		if(!map.containsKey(mapKey)) {
			
		    long size = json.toString().length() / 1024;
		    
		    if(size > 16) {
		    	
		    	throw new CustomException("Error: Size of JSON file is more than 16kB\nCreate Unsuccessfull");
		    	
		    }
		    
		    map.put(mapKey, json);
		    
		    writeInFile(fn);
		}
		
		else
			throw new CustomException("Key : " + mapKey + " already exists\\nCreate Unsuccessfull");
		
		return "Create successfull";
		
	}
	
	public static String createJSON(String mapKey, JSONObject json) throws CustomException {
		return createJSON(filename, mapKey, json);
	}
	
	public static JSONObject readJSON(String fn, String key)  throws CustomException {

		if(map.containsKey(key)) {
			
			return map.get(key);
		}
		throw new CustomException("Error : Key not found");
		
	}
	
	public static JSONObject readJSON(String mapKey) throws CustomException {
		return readJSON(filename, mapKey);
	}
	
	
	public static String deleteJSON(String fn, String key)  throws CustomException {

		if(map.containsKey(key)) {
			
			map.remove(key);
			writeInFile(fn);
			return "Delete successfull";
			
		}
		
		throw new CustomException("Error : Key not found : " + key + "\nDelete Unsuccessfull");
	}
	
	public static String deleteJSON(String mapKey) throws CustomException {
		return deleteJSON(filename, mapKey);
	}
	
	
	@SuppressWarnings("unchecked")
	private static void writeInFile(String fn) throws CustomException {
  	  	
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.putAll( map );
	    
	    double size = jsonObject.toString().length()/(1024*1024*1024);
	    
	    if(size > 1) {
	    	throw new CustomException("Error : File size is greater than 1GB");
	    }
	    
	    try {

	          FileWriter file = new FileWriter(fn);
	         
	          file.write(jsonObject.toJSONString());
	         
	          file.close();
	         
	      } catch (IOException e) {
	    	  
	         e.printStackTrace();
	      }
	    
	}
}

