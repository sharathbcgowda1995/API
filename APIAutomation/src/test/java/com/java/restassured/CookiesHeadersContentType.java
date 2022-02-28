package com.java.restassured;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CookiesHeadersContentType {
	@Test
	public void passTheJsonFile() {
		Response resp =
			given()
				.param("page", 2)
			.when()
				.get("https://reqres.in/api/users");
		
		System.out.println(resp.prettyPrint());
		
		System.out.println("Header -------: " + resp.getHeader("Content-Type"));
		System.out.println("Headers -------: " + resp.getHeaders());
		System.out.println("Cookies --------: " + resp.getCookies());
		System.out.println("Contet-Type -------: " + resp.getContentType()  );

	}
}
