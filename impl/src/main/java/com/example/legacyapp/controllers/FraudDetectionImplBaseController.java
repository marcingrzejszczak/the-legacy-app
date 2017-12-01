package com.example.legacyapp.controllers;

import com.example.legacyapp.dto.Charges;
import com.example.legacyapp.services.CustomerRentalHistoryManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcin Grzejszczak
 */
@RestController
public class FraudDetectionImplBaseController {

	// field injection and package tangling FTW!
	@Autowired CustomerRentalHistoryManager manager;

	// legacy API
	@GetMapping(value = "/customer/get/fraud/get",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<String> collection() {
		// anemic!
		if (manager.listAllCharges().getCharges().isEmpty()) {
			return ResponseEntity
					.accepted()
					.build();
		}
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body("Pay your debts first");
	}

}
