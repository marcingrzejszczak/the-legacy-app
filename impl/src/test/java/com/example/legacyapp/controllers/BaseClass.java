package com.example.legacyapp.controllers;

import java.util.Collections;

import com.example.legacyapp.dto.Charge;
import com.example.legacyapp.dto.Charges;
import com.example.legacyapp.services.CustomerRentalHistoryManager;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

/**
 * @author Marcin Grzejszczak
 */
public class BaseClass {

	@Before
	public void setup() {
		FraudDetectionImplBaseController controller = new FraudDetectionImplBaseController();
		controller.manager = new CustomerRentalHistoryManager() {
			@Override public Charges listAllCharges(String name) {
				if (name.equals("I_AM_FRAUD")) {
					return new Charges(Collections.singletonList(new Charge()));
				}
				return new Charges();
			}
		};
		RestAssuredMockMvc.standaloneSetup(controller);
	}
}
