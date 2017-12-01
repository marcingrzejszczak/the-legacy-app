package com.example.legacyapp.services;

import com.example.legacyapp.TheLegacyApp;
import com.example.legacyapp.dto.Charges;
import org.assertj.core.api.BDDAssertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

/**
 * Version of the test with stub runner
 *
 * @author Marcin Grzejszczak
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TheLegacyApp.class,
		webEnvironment = NONE)
@AutoConfigureStubRunner(
		ids = "com.example.github:github-webhook:+:stubs:7654",
		repositoryRoot = "http://repo.spring.io/libs-milestone-local"
)
public class OnlineModeTests {

	@Test
	public void should_return_charge_collection() {
		String object = new RestTemplate()
				.getForObject("http://localhost:7654/", String.class);

		BDDAssertions.then(object).contains("dsyer");
	}
}