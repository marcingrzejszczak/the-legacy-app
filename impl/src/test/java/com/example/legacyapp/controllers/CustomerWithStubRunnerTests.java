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
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

/**
 * Version of the test with stub runner
 *
 * @author Marcin Grzejszczak
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TheLegacyApp.class,
		properties = "stripe.url=http://localhost:8765",
		webEnvironment = NONE)
@AutoConfigureStubRunner(
		ids = "com.example:the-legacy-app-stubs:+:stubs:8765"
		// in Intellij you have to use workOffline
		,		workOffline = true
)
public class CustomerWithStubRunnerTests {

	@Autowired CustomerRentalHistoryControllerImplBase base;

	@Test
	public void should_return_charge_collection() {
		ResponseEntity<Charges> entity = base.collection();

		BDDAssertions.then(entity.getBody().getCharges())
				.hasSize(25);
	}
}