package com.java.pojosimple.payload;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class TestOrPojoCreationClass {

	public static void main(String[] args) {

		MasterPojo pojo = new MasterPojo("Sharath", 17, new String[] { "Java", "API Automation" }, "CG",
				"sharath@gmail.com");

		
		//Start writing the code for API automation
		
		Response resp =given()
			.body(pojo).log().all()
		.when()
		  	.post("https://reqres.in/api/users");
		
		System.out.println("Pojo creation : " + resp.prettyPrint());
	}

}
