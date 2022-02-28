package com.java.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MultipleWaysToValidate {

		@Test
		public void passTheJsonFile() {
			Response resp =
				given()
					.param("page", 2)
				.when()
					.get("https://reqres.in/api/users");
			
			System.out.println(resp.prettyPrint());
			
			Assert.assertEquals(resp.getBody().path("data[0].first_name"), "Michael");
			//Assert.assertEquals(resp.getPath().get("data[0].first_name") , "Michael");

		}
		
		@Test
		public void otherWay() {
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
			//mutiple attributes are validating in single go
			.body("page", equalTo(2),
					"data[0].id",equalTo(7))
			.log().all();
		}

}
