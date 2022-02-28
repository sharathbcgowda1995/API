package com.java.pojosimple.payload;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class Deserialization {
		@Test
		public static void serilalized() throws Exception {
			

			MasterPojo pojo = new MasterPojo("Sharath", 17, new String[] { "Java", "API Automation" }, "CG",
					"sharath@gmail.com");
			

			Response resp = 
					given()
						.body(pojo).log().all()
					.when()
						.post("https://reqres.in/api/users");
			

			System.out.println("Pojo creation : " + resp.prettyPrint());
			Assert.assertEquals(resp.getStatusCode(), 201);
			
			String response = resp.asString();
			
			ObjectMapper om = new ObjectMapper();
			MasterPojo po = om.readValue(response, MasterPojo.class);
			
			System.out.println("Skills are as follows : " + po.getName());
			
			
		}
}
