package com.sgtesting.restapi;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;



public class ReadJSONFile {

	@Test
	public void demo1()
	{
		File f1 = null;
		FileReader fr = null;
		try {
			f1 = new File(".\\DataFiles\\Student.json");
			fr = new FileReader(f1);
			  
			Object obj = new JSONParser().parse(fr);
			JSONObject jsonObject = (JSONObject)obj;
			String fname = (String) jsonObject.get("fname");
			System.out.println(fname);
			
			long age = (long) jsonObject.get("age");
			System.out.println(age);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
}
