package com.main.FreshworksApplication;

import org.json.simple.JSONObject;

import com.main.FreshworksApplication.Freshworks;
import com.main.FreshworksApplication.CustomException;

public class Test {

	public static void main(String[] args) throws CustomException {
		
		JSONObject json = new JSONObject();
		json.put("a", "b");
		
		Freshworks.init();
		
		System.out.println(Freshworks.createJSON("anu", json));
		
		try {
			Freshworks.createJSON("anu", json);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		json = Freshworks.readJSON("anu");
		System.out.println(json.toString());
		
		try {
			Freshworks.readJSON("an");
		}catch(Exception e) {
			System.out.println(e);
		}
		
		try {
			Freshworks.deleteJSON("anu");
		} catch (CustomException e1) {
			e1.printStackTrace();
		}
		
		try {
			Freshworks.deleteJSON("nu");
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
