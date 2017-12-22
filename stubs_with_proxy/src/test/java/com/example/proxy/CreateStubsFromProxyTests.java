package com.example.proxy;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.recording.RecordSpecBuilder;

@RunWith(SpringRunner.class)
public class CreateStubsFromProxyTests extends AbstractStubsFromProxy {

	@Override protected RecordSpecBuilder configure(RecordSpecBuilder builder) {
		return builder.forTarget("https://api.stripe.com/");
	}

	@Override protected RestTemplateBuilder configure(RestTemplateBuilder builder) {
		return builder.basicAuthorization("sk_test_BQokikJOvBiI2HlWgH4olfQ2", null);
	}

	@Test
	public void should_return_list_of_charges() {
		ResponseEntity<String> response = restTemplate
				.getForEntity("/v1/charges?limit=25", String.class);

		then(response.getStatusCodeValue()).isEqualTo(200);
		then(response.getBody()).isNotBlank();
	}


}
