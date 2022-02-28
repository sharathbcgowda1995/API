package com.java.restassured;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ResponseTime {
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

			System.out.println(resp.getTime());
			System.out.println(resp.getTimeIn(TimeUnit.SECONDS));
			System.out.println(resp.time());
			System.out.println(resp.timeIn(TimeUnit.SECONDS));
			
			int arr[] = new int[2];
			 arr= new int[3];
			 
			 for (int i : arr) {
				System.out.println(i);
			}
		}
}
