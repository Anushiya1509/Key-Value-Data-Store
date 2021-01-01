package com.test.FreshworksApplication;

import static org.junit.Assert.*;

import org.junit.Test;

import org.json.simple.JSONObject;

import com.main.FreshworksApplication.Freshworks;
import com.main.FreshworksApplication.CustomException;

public class FreshworksApplicationTest {

	Freshworks freshworks = new Freshworks();
	String fn  = "D:\\new.txt";
	JSONObject json =  new JSONObject();
	
	@SuppressWarnings("unchecked")
	@Test
	public void createJSONtest() throws CustomException{
		json.put("name", "anu");
		String test = Freshworks.createJSON("key", json);
		assertEquals("Create successfull", test);
	}
	@SuppressWarnings("unchecked")
	@Test(expected = CustomException.class)
	public void createJSONExceptiontest() throws CustomException{
		json.put("name", "anu");
		Freshworks.createJSON("1234567891234567890123456789012312", json);
		
	}
	@SuppressWarnings("unchecked")
	@Test
	public void readJSONtest() throws CustomException{
		json.put("name", "anu");
		Freshworks.createJSON("create", json);
		JSONObject test = Freshworks.readJSON("create");
		assertEquals(test,json);
	}
	@Test(expected = CustomException.class)
	public void readJSONException() throws CustomException {
		Freshworks.readJSON("testkey");
	}
	@Test
	public void deleteJSON() throws CustomException {
		json.put("name", "anu");
		Freshworks.createJSON("delete", json);
		String deletetest = Freshworks.deleteJSON("delete");
		assertEquals("Delete successfull" , deletetest);
	}
	@Test(expected = CustomException.class)
	public void deleteJSONException() throws CustomException {
		Freshworks.deleteJSON("testkey");
	}

}
