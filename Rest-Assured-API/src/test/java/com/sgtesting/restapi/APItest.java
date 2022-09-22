package com.sgtesting.restapi;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
public class APItest {

	@Test(priority =1)
	public void user1Post()
	{
		try 
		{

			RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1/Activities";
			RequestSpecification httpRequest = RestAssured.given();
			
			File f1= new File(".\\DataFiles\\PostActivities.json");
			httpRequest.header("COntent-Type","application/json");
			httpRequest.body(f1);
			
			Response response = httpRequest.post();
			String content = response.asPrettyString();
			System.out.println(content);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority =2)
	public void getUser()
	{
		try 
		{
			
			RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/Activities";
			RequestSpecification httpRequest = RestAssured.given();
			
			Response response = httpRequest.get();
			String content = response.asPrettyString();
			System.out.println(content);
			
			Headers headers = response.getHeaders();
			for(Header data:headers)
			{
				System.out.println(data);
				
				Assert.assertEquals("application/json; charset=utf-8; v=1.0", headers.getValue("Content-Type"));;
			}
			
			given().when().get().then().assertThat().statusCode(200).log().all()
			.body(JsonSchemaValidator.matchesJsonSchema(new File(".\\DataFiles\\jsonschema.json"))).log().all();
			
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	@Test(priority =3)
	public void deleteUser()
	{
		try 
		{
			
			RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/Users/0";
			RequestSpecification httpRequest = RestAssured.given();
			
			Response response = httpRequest.delete();
			String content = response.asString();
			System.out.println(content);
			
			
			
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
