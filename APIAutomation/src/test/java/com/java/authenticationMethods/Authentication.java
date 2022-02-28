package com.java.authenticationMethods;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.response.Response;

public class Authentication {
	//@Test
		public void passTheJsonFile() {
			Response resp = 
				given()
					//.auth().preemptive().basic(username, password)
					//.auth().digest(userName, password)
					//.auth().oauth2(accessToken)
					//.auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
					.body(new File("./payLoad.json")).log().all()
				.when()
					.post("https://reqres.in/api/users");

			System.out.println("Usin the json file : " + resp.prettyPrint());

			System.out.println("STSTUS CODE IS : " + resp.getStatusCode());
		}
}
