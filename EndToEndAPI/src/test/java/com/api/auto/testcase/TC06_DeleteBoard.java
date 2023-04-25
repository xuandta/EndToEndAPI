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

public class TC06_DeleteBoard {

	Response response;
	
	@BeforeClass
	public void init() {
		
		RestAssured.baseURI = PropertiesFileUtils.getProperty("baseurl");
		RestAssured.basePath = PropertiesFileUtils.getProperty("PathDeleteBoard");

		RequestSpecification request = RestAssured.given()
														.contentType(ContentType.JSON)
														.param("key", PropertiesFileUtils.getProperty("key"))
														.param("token", PropertiesFileUtils.getProperty("token"));
		response = request.delete(PropertiesFileUtils.getVariable("idFirstBoard"));
		System.out.println("************************ Response **********************");
		System.out.println(response.asPrettyString());
	}
	
	@Test
	public void Verify_Status_Code() {
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void idFirstBoard_deleted() {
		assertEquals(response.body().toString().contains(PropertiesFileUtils.getVariable("idFirstBoard")), false);
	}

	
}
