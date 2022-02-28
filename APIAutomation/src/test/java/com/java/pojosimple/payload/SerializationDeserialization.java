package com.java.pojosimple.payload;
import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class SerializationDeserialization {

	//@Test
	public static void serilalized() throws Exception {
		MasterPojo pojo = new MasterPojo("Sharath", 17, new String[] { "Java", "API Automation" }, "CG",
				"sharath@gmail.com");

		// Serialization using the ObjectMapper
		
		ObjectMapper om = new ObjectMapper();
		String serializaedObjectToByteStream=om.writeValueAsString(pojo);

		Response resp = 
				given()
					.body(serializaedObjectToByteStream).log().all()
				.when()
					.post("https://reqres.in/api/users");

		System.out.println("Pojo creation : " + resp.prettyPrint());
		Assert.assertEquals(resp.getStatusCode(), 201);
	}
	
	@Test
	public static void serilalizedusingSecondWay() throws Exception {
		MasterPojo pojo = new MasterPojo("Sharath", 17, new String[] { "Java", "API Automation" }, "CG",
				"sharath@gmail.com");

		
		
		ObjectMapper om = new ObjectMapper();
		
		String outputfile = System.getProperty("user.dir");
		File f = new File(outputfile+"/src/test/resources/payloadCreatedByFile.json");
		
		om.writerWithDefaultPrettyPrinter().writeValue(f, pojo);
		
		Response resp = 
				given()
					.body(new File(outputfile+"/src/test/resources/payloadCreatedByFile.json")).log().all()
				.when()
					.post("https://reqres.in/api/users");

		System.out.println("Pojo creation : " + resp.prettyPrint());
		Assert.assertEquals(resp.getStatusCode(), 201);
	}
}
