package com.example.legacyapp.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.example.legacyapp.dto.Charge;
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
		List<Charge> charges = manager.listAllCharges(name).getCharges();
		if (charges.isEmpty()) {
			return ResponseEntity
					.accepted()
					.body(new FraudResponse("You're not a fraud", UUID.randomUUID().toString()));
		}
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new FraudResponse("Pay your debts first", UUID.randomUUID().toString()));
	}

}

@Data
@AllArgsConstructor
class FraudResponse {
	private String message;
	private String ex27;
}
