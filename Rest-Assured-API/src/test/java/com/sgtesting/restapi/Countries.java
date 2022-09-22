package com.sgtesting.restapi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;

public class Countries {

	@Test()
	public void getCountry()
	{
		try {
			
			RestAssured.baseURI = "https://restcountries.com/v2/capital/delhi";
			RequestSpecification httpRequest = RestAssured.given();
			
			Response response = httpRequest.get();
			String content = response.asPrettyString();
			System.out.println(content);
			
			Headers headers = response.getHeaders();
			for(Header  data : headers)
			{
				System.out.println(data);
				
			}
			
			given().when().get().then()
			.assertThat().statusCode(200)
			.body(JsonSchemaValidator.matchesJsonSchema(new File(".\\DataFiles\\countryschema.json")));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
