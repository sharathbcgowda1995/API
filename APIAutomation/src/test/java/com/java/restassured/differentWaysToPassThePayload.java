package com.java.restassured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class differentWaysToPassThePayload {
		// @Test
	public void waysOfExtractingMap() {

		// 1.using hashmap - required jackson databind dependency
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "Sharath");
		map.put("job", "Software Engineer");

		baseURI = "https://reqres.in/api";
		basePath = "/users";

		Response resp = given().body(map).log().all().when().post();

		System.out.println("using map functionality : " + resp.prettyPrint());

	}

//		@Test
//		public void waysOfpassingPayloadSTRINGS() {
//			
//			//1.using escape sequence  - required jackson databind dependency
//			String payload = "{
//				    \"name\": \"morpheus\",
//				    \"job\": \"leader\"
//				}";
//			
//			baseURI = "https://reqres.in/api";
//			basePath = "/users";
//			
//			Response resp =	
//				given()
//					.body(payload).log().all()
//				.when()
//					.post();
//			
//			System.out.println("using map functionality : " + resp.prettyPrint());
//			
//		}

	// 3.using payload
	//@Test
	public void passTheJsonFile() {
		Response resp = given().body(new File("./payLoad.json")).log().all().when().post("https://reqres.in/api/users");

		System.out.println("Usin the json file : " + resp.prettyPrint());

		System.out.println("STSTUS CODE IS : " + resp.getStatusCode());
	}

	// 4.HashMap in some complex way
	// @Test
	public void hashmapComplex() {

		// key-value
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Sharath");
		map.put("age", 26);

		// key-arrayValues
		HashSet<Object> set = new HashSet<Object>();
		set.add("java");
		set.add("API Automation");
		map.put("skills", set);

		// key-{key-value}
		HashMap<Object, Object> multipleData = new HashMap<Object, Object>();
		multipleData.put("Companyname", "Capgemini");
		multipleData.put("emailid", "sharathbcgowda2@gmail.com");
		map.put("details", multipleData);

		Response resp = given().body(map).log().all().when().post("https://reqres.in/api/users");
	}

	@Test
	public void usingJsonObjectClass() {
		JSONObject jo = new JSONObject();
		jo.put("name", "Sharath");
		jo.put("age", 16);

		JSONArray ja = new JSONArray();
		ja.put("Java");
		ja.put("API automation");

		jo.put("skills", ja);

		JSONObject jo1 = new JSONObject();
		jo.put("company", "Capgemini");
		jo.put("job", "Software Engineer");

		jo.put("details", jo1);

		Response resp = given().body(jo.toString()).log().all().when().post("https://reqres.in/api/users");

		System.out.println("Print the output : " + resp.prettyPrint());

	}

}
