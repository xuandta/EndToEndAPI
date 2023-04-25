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

public class TC03_TaoCard {

	Response response;
	
	@BeforeClass
	public void init() {
		
		RestAssured.baseURI = PropertiesFileUtils.getProperty("baseurl");
		RestAssured.basePath = PropertiesFileUtils.getProperty("PathCard");
		
		Map<String, Object> body = new HashMap<String,Object>();
		body.put("name", PropertiesFileUtils.getProperty("NameCard"));
		body.put("idList", PropertiesFileUtils.getVariable("idList"));
		body.put("key", PropertiesFileUtils.getProperty("key"));
		body.put("token", PropertiesFileUtils.getProperty("token"));
		

		RequestSpecification request = RestAssured.given()
														.contentType(ContentType.JSON)
														.body(body); 
		response = request.post();
		System.out.println("************************ Response **********************");
		System.out.println(response.asPrettyString());
	}
	
	@Test
	public void Verify_Status_Code() {
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void Verify_NameCard() {
		assertEquals(response.jsonPath().getString("name"),PropertiesFileUtils.getProperty("NameCard"),"Không khớp NameCard");
		String idCard = response.jsonPath().getString("id");
		System.out.println("idCard là:  " + idCard);
		PropertiesFileUtils.saveVariable("idCard", idCard);
	}
	
}
