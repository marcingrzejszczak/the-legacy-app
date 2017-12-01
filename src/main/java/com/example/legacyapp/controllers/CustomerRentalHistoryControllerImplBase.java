package com.example.legacyapp.controllers;

import com.example.legacyapp.services.CustomerRentalHistoryManagerImpl;
import com.google.gson.Gson;
import com.stripe.model.ChargeCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcin Grzejszczak
 */
@RestController
public class CustomerRentalHistoryControllerImplBase {

	// field injection and package tangling FTW!
	@Autowired CustomerRentalHistoryManagerImpl impl;

	// legacy API
	@GetMapping(value = "/customer/rental/history/get",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<String> collection() {
		return ResponseEntity.ok(new Gson().toJson(impl.listAllCharges()));
	}

}
