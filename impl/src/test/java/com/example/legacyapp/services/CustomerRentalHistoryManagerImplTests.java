package com.example.legacyapp.services;

import com.example.legacyapp.TheLegacyApp;
import com.example.legacyapp.dto.Charges;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

/**
 * @author Marcin Grzejszczak
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TheLegacyApp.class,
		CustomerRentalHistoryManagerImplTests.Config.class},
		webEnvironment = NONE)
public class CustomerRentalHistoryManagerImplTests {

	@Autowired CustomerRentalHistoryManager manager;

	@Test
	public void should_return_charge_collection() {
		Charges charges = manager.listAllCharges("foo");

		BDDAssertions.then(charges.getCharges())
				.extracting("customer")
				.containsExactly("a", "b", "c");
	}

	@Configuration
	static class Config {
		// primary FTW!
		@Bean @Primary CustomerRentalHistoryManager manager() {
			return new TheStub();
		}
	}
}