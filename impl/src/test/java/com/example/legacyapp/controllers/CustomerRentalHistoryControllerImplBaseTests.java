package com.example.legacyapp.controllers;

import com.example.legacyapp.TheLegacyApp;
import com.example.legacyapp.dto.Charges;
import com.example.legacyapp.services.CustomerRentalHistoryManager;
import com.example.legacyapp.services.TheStub;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

/**
 * @author Marcin Grzejszczak
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TheLegacyApp.class,
		CustomerRentalHistoryControllerImplBaseTests.Config.class},
		webEnvironment = NONE)
public class CustomerRentalHistoryControllerImplBaseTests {

	@Autowired CustomerRentalHistoryControllerImplBase base;

	@Test
	public void should_return_charge_collection() {
		ResponseEntity<Charges> entity = base.collection();

		BDDAssertions.then(entity.getBody().getCharges())
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