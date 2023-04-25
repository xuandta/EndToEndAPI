package com.api.auto.testcase;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.auto.utils.PropertiesFileUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC04_UpdateCard {

	Response response;
	
	@BeforeClass
	public void init() {
		
		RestAssured.baseURI = PropertiesFileUtils.getProperty("baseurl");
		RestAssured.basePath = PropertiesFileUtils.getProperty("PathCard");
		
		Map<String, Object> body = new HashMap<String,Object>();
		body.put("key", PropertiesFileUtils.getProperty("key"));
		body.put("token", PropertiesFileUtils.getProperty("token"));
		body.put("name", PropertiesFileUtils.getProperty("NameCard2"));
		body.put("closed", false);	
		body.put("idList", PropertiesFileUtils.getVariable("idList"));
		body.put("idBoard", PropertiesFileUtils.getVariable("idBoard"));	

		RequestSpecification request = RestAssured.given()
														.contentType(ContentType.JSON)
														.body(body); 
		response = request.put("/"+PropertiesFileUtils.getVariable("idCard"));
		System.out.println("************************ Response **********************");
		System.out.println(response.asPrettyString());
	}
	
	@Test
	public void Verify_Status_Code() {
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void Verify_NameCard() {
		assertEquals(response.jsonPath().getString("name"),PropertiesFileUtils.getProperty("NameCard2"),"Không khớp NameCard");
	}
	
}
