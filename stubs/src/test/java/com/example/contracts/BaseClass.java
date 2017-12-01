package com.example.contracts;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import org.assertj.core.api.BDDAssertions;
import org.junit.Before;

/**
 * @author Marcin Grzejszczak
 */
public class BaseClass {

	@Before
	public void setup() {
		RestAssured.baseURI =
				"https://api.stripe.com/";
		RestAssured.authentication =
				RestAssured.basic("sk_test_BQokikJOvBiI2HlWgH4olfQ2", "");
	}

	public void assertResponse(String response) {
		BDDAssertions.then(response)
				.isNotEmpty()
				.contains("\"object\": \"charge\"");
	}
}
