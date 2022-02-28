package com.java.restassured;

//Basic Concepts with the Get,Post,Put,del
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BasicsOfRestAssured {

	//@Test
	public void usingGet() {

		given()
			 ///api/users?pag=2
			.param("page", 2)
			.header("content-Type","application/json")
			.contentType(ContentType.JSON)
			.auth().none().log().all()
			
		.when()
			.get("https://reqres.in/api/users")

		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
	//we can use the same body meth to validate the all the components
	//@Test
		public void usingGet2() {

			given()
				 ///api/users?pag=2
				.param("page", 2)
				.header("content-Type","application/json")
				.contentType(ContentType.JSON)
				.auth().none().log().all()
				
			.when()
				.get("https://reqres.in/api/users")

			.then()
				.statusCode(200)
				.body("page", equalTo(2),
						"data[0].id",equalTo(7))
				.log().all();
		}
		
	
/****************************************************************************************************************/	
	//@Test
	public void usingPostCall() {
		//Prepare the URL
		RestAssured.baseURI = "https://reqres.in/api";
		basePath = "/users";
		
		//Prepare the data - to use this we need jackson databind dependency.
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("name", "Sharath");
		map.put("job", "Software Engineer");
		
		//1.Store the response in the Response interface.
		Response resp =	given()
						.body(map)
						.contentType(ContentType.JSON).log().all()
					.when()
						.post();
		
		//1.Print the Logs
		resp.prettyPrint();
	}
	
		
/**********************************************************************************************/
	
	/*-------------------------------------------------------------------*/
	
	@Test
	public void usingPostCall3() {
		//Prepare the URL
		RestAssured.baseURI = "https://reqres.in/api";
		basePath = "/users";
		
		//Multiple methods using the response
		Response resp =	given()
						.param("page", 2)
						.contentType(ContentType.JSON).log().all()
					.when()
						.get();
		
		//1.Print the Logs
		System.out.println("Printed the whole response : " + resp.asString());
		
		//2.get statuscode
		System.out.println("Status code is : " + resp.getStatusCode());
		
		//3.get time 
		System.out.println("Total time taken to complete the oprtn : " + resp.getTime());
		
		//4.get cookies
		System.out.println("Cookies are : " + resp.getCookies());
		
		//5.to print we have one more method.
		System.out.println("Using pretty print : " + resp.prettyPrint());
		
	}

}
