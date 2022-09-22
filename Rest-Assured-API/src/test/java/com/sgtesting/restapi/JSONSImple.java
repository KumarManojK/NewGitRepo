package com.sgtesting.restapi;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;
import io.restassured.path.json.JsonPath;




public class JSONSImple {

	public static void main(String[] args) {
		readContent();

	}

	public static void readContent()
	{

		File f1 = null;
		FileReader fr = null;
		try 
		{


			f1 = new File(".\\DataFiles\\Student.json");
			fr  = new FileReader(f1);

			Object obj = new JSONParser().parse(fr);
			JSONObject jsonObject = (JSONObject)obj;
			
			String fname = (String)jsonObject.get("fname");
			System.out.println(fname);
			
			long age = (long)jsonObject.get("age");
			System.out.println(age);
			



		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
