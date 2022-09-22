package com.sgtesting.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserScenario {
	RequestSpecification httpRequest;
	Response response;
	public static String auth_Token = null;

	@Test(priority=1)
	@Given("^I provided Post Request Endpoint$")
	public void I_provided_Post_Request_Endpoint() 
	{
		try 
		{
			RestAssured.baseURI = "https://enigmatic-meadow-39517.herokuapp.com/api/v1/students/sign_in";


		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Test(priority=2)
	@And("^I created Request Specification object$")
	public void I_created_Request_Specification_object()
	{
		try 
		{
			 httpRequest = RestAssured.given();


		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Test(priority=3)
	@Then("^I provided File to request body$")
	public void I_provided_File_to_request_body()
	{
		try 
		{
			File f1 = new File(".\\DataFiles\\SignIn.json");

			httpRequest.header("Content-Type","application/json");
			httpRequest.body(f1);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	@Test(priority=4)
	@Then("^I Execute Post method$")
	public void I_Execute_Post_method()
	{
		try {
			
			response = httpRequest.post();
			String Content = response.asPrettyString();
			System.out.println(Content);
			

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=5)
	@And("^I capture auth_Token$")
	public void I_capture_auth_Token() 
	{
		try 
		{
			
			JsonPath jpath = response.jsonPath();
			auth_Token = jpath.getString("auth_token");
			System.out.println("AUTH_TOKEN :"+auth_Token);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	@Test(priority=6)
	@Then("^I got Status code as 200$")
	public void I_got_Status_code_as_200()
	{
		try 
		{
			
			int statuscode = response.getStatusCode();
			System.out.println("Status Code :"+statuscode);
			Assert.assertEquals(statuscode, 200);
			
			Headers headers = response.getHeaders();
			for(Header data : headers)
			{
				System.out.println(data);
				
				Assert.assertEquals("application/json; charset=utf-8", headers.getValue("Content-Type"));
			}
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}


