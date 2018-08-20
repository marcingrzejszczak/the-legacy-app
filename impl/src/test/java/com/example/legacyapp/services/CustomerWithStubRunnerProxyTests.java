package com.example.legacyapp.services;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.legacyapp.dto.Charges;

/**
 * Version of the test with stub runner
 *
 * @author Marcin Grzejszczak
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerWithStubRunnerProxyTests.Config.class,
		properties = "api.url=http://localhost:6765",
		webEnvironment = NONE)
@AutoConfigureStubRunner(
		ids = "com.example:the-legacy-app-stubs-with-proxy:+:stubs:6765"
		// in Intellij you have to use workOffline
		,		stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
public class CustomerWithStubRunnerProxyTests {

	@Autowired CustomerRentalHistoryManager manager;

	@Test
	public void should_return_charge_collection() {
		Charges charges = manager.listAllCharges("foo");

		BDDAssertions.then(charges.getCharges())
				.hasSize(25);
	}

	@Configuration
	@EnableAutoConfiguration
	static class Config {
		@Bean
		CustomerRentalHistoryManager manager() {
			return new CustomerRentalHistoryManagerImpl();
		}
	}
}