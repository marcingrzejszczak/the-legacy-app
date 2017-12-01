package com.example.legacyapp.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.legacyapp.dto.Charges;
import com.example.legacyapp.services.CustomerRentalHistoryManager;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcin Grzejszczak
 */
@RestController
public class FraudDetectionImplBaseController {

	// field injection and package tangling FTW!
	@Autowired CustomerRentalHistoryManager manager;

	// legacy API
	@GetMapping(value = "/customer/{name}/get/fraud/get/",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<FraudResponse> frauds(@PathVariable String name) {
		// I smell NPE
		if (manager.listAllCharges().getCharges().isEmpty()) {
			return ResponseEntity
					.accepted()
					.body(new FraudResponse(name + ", you're not a fraud"));
		}
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new FraudResponse(name + ", pay your debts first"));
	}

}

@Data
class FraudResponse {
	private String ex27;
	private String message;

	public FraudResponse(String message) {
		this.message = message;
	}
}
