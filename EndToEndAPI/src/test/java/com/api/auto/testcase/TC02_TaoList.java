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

public class TC02_TaoList {

	Response response;
	
	@BeforeClass
	public void init() {
		
		RestAssured.baseURI = PropertiesFileUtils.getProperty("baseurl");
		RestAssured.basePath = PropertiesFileUtils.getProperty("PathList");
		
		Map<String, Object> body = new HashMap<String,Object>();
		body.put("name", PropertiesFileUtils.getProperty("NameList"));
		body.put("idBoard", PropertiesFileUtils.getVariable("idBoard"));
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
	public void Verify_NameList() {
		assertEquals(response.jsonPath().getString("name"),PropertiesFileUtils.getProperty("NameList"),"Không khớp NameList");
	}
	
	@Test
	public void idList_existed() {
		assertEquals(response.jsonPath().getString("id").isEmpty(), false);
		String idList = response.jsonPath().getString("id");
		System.out.println("idList là:  " + idList);
		PropertiesFileUtils.saveVariable("idList", idList);
	}
	
}
