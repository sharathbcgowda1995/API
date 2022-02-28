package com.java.restassured;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class JavaResponseWithNestedParsing {

	public static void parseObject(JSONObject json, String value) {
		System.out.println(json.get(value));
	}

	public static void getKey(JSONObject json, String key) {

		// 1st check for nested array and Objects
		boolean exists = json.has(key);
		if (exists) {
			parseObject(json, key);
		} else {
			
			//Get all the keys
			Iterator<?> iterator = json.keys();
			
			//iterate over a while loop to check object and arrays
			while (iterator.hasNext()) {
				
				String keyContainingArrayOrObject = (String) iterator.next();

				//If Key is object use recursion
				if (json.get(keyContainingArrayOrObject) instanceof JSONObject) {

					if (exists == false) {
						getKey(json.getJSONObject(keyContainingArrayOrObject), key);
					}
					
					//if key is array convert that to array and use loop to get each array element convert them
					//as a string and then again use that as input for inner json Object again use the recursive method
					//with the new json and the same old key.
				} else if (json.get(keyContainingArrayOrObject) instanceof JSONArray) {
					JSONArray jsonarray = json.getJSONArray(keyContainingArrayOrObject);
					for (int i = 0; i < jsonarray.length(); i++) {
						String st = jsonarray.get(i).toString();
						JSONObject innerJson = new JSONObject(st);
						if (exists == false) {
							getKey(innerJson, key);
						}
					}

				}
			}
		}
	}

	public static void main(String[] args) {

		String stringConverted = "{\r\n" + "	\"id\": \"0001\",\r\n" + "	\"type\": \"donut\",\r\n"
				+ "	\"name\": \"Cake\",\r\n" + "	\"ppu\": 0.55,\r\n" + "	\"batters\":\r\n" + "		{\r\n"
				+ "			\"batter\":\r\n" + "				[\r\n"
				+ "					{ \"id\": \"1001\", \"type\": \"Regular\" },\r\n"
				+ "					{ \"id\": \"1002\", \"type\": \"Chocolate\" },\r\n"
				+ "					{ \"id\": \"1003\", \"type\": \"Blueberry\" },\r\n"
				+ "					{ \"id\": \"1004\", \"type\": \"Devil's Food\" }\r\n" + "				]\r\n"
				+ "		},\r\n" + "	\"topping\":\r\n" + "		[\r\n"
				+ "			{ \"id\": \"5001\", \"type\": \"None\" },\r\n"
				+ "			{ \"id\": \"5002\", \"type\": \"Glazed\" },\r\n"
				+ "			{ \"id\": \"5005\", \"type\": \"Sugar\" },\r\n"
				+ "			{ \"id\": \"5007\", \"type\": \"Powdered Sugar\" },\r\n"
				+ "			{ \"id\": \"5006\", \"type\": \"Chocolate with Sprinkles\" },\r\n"
				+ "			{ \"id\": \"5003\", \"type\": \"Chocolate\" },\r\n"
				+ "			{ \"id\": \"5004\", \"type\": \"Maple\" }\r\n" + "		]\r\n" + "}";

		JSONObject json = new JSONObject(stringConverted);

		getKey(json, "id");

	}

}
