package com.sgtesting.tests;

import java.io.File;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserScenarioDemoPatch {
	public static String auth_Token = null;
	public static String user_id = null;
	@Test(priority=1)
	public void signin()
	{
		try
		{
			RestAssured.baseURI = "https://enigmatic-meadow-39517.herokuapp.com/api/v1/students/sign_in";
			RequestSpecification httpRequest = RestAssured.given();

			String path = System.getProperty("user.dir");
			File f1 = new File(path+"\\DataFiles\\SignIn.json");

			httpRequest.header("Content-Type","application/json");
			httpRequest.body(f1);

			Response response = httpRequest.post();
			System.out.println(response.asString());

			JsonPath jpath = response.jsonPath();
			auth_Token = jpath.getString("auth_token");


		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test(priority=2)
	public void createuser()
	{
		try
		{
			RestAssured.baseURI = "https://enigmatic-meadow-39517.herokuapp.com/api/v1/users";
			RequestSpecification httprequest = RestAssured.given();

			String path = System.getProperty("user.dir");
			File f1 = new File(path+"\\DataFiles\\CreateUSer.json");

			httprequest.header("Content-Type","application/json");
			httprequest.header("Authorization",auth_Token);
			httprequest.body(f1);

			Response response = httprequest.post();
			System.out.println(response.asPrettyString());

			JsonPath jpath = response.jsonPath();
			user_id = jpath.getString("id");


		}catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	@Test(priority=3)
	public void getuser()
	{
		try {
			
			String path = System.getProperty("user.dir");
			RestAssured.baseURI = "https://enigmatic-meadow-39517.herokuapp.com/api/v1/users/"+user_id;
			RequestSpecification httprequest = RestAssured.given();

			String result = given().header("Authorization",auth_Token).when().get()
			.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(".\\DataFiles\\Schema.json")))
			.toString();
			
			System.out.println("Schema Result :"+result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test(priority = 4)
//	public void modifyuser()
//	{
//		try 
//		{
//			RestAssured.baseURI="https://enigmatic-meadow-39517.herokuapp.com/api/v1/users/"+user_id;
//			RequestSpecification httpRequest = RestAssured.given();
//
//			String path = System.getProperty("user.dir");
//			File f1 = new File(path+"\\DataFiles\\ModifyUser.json");
//
//			httpRequest.header("Content-Type","application/json");
//			httpRequest.header("Authorization",auth_Token);
//			httpRequest.body(f1);
//
//			Response response = httpRequest.patch();
//			System.out.println(response.asString());
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}


//	@Test(priority=5)
//	public void deleteUdser()
//	{
//		try {
//
//			RestAssured.baseURI="https://enigmatic-meadow-39517.herokuapp.com/api/v1/users/"+user_id;
//			RequestSpecification httpRequest = RestAssured.given();
//
//			httpRequest.header("Content-Type","application/json");
//			httpRequest.header("Authorization",auth_Token);
//
//			Response response = httpRequest.delete();
//			System.out.println(response.asString());
//
//
//
//		} catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//	}
//	@Test(priority=6)
//	public void signOut()
//	{
//		try 
//		{
//			RestAssured.baseURI="https://enigmatic-meadow-39517.herokuapp.com/api/v1/students/sign_out";
//			RequestSpecification httpRequest = RestAssured.given();
//
//			httpRequest.header("Authorization",auth_Token);
//		} catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//	}
//
//
}
