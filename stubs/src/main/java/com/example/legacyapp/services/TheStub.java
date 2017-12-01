package com.example.legacyapp.services;

import java.util.Arrays;

import com.example.legacyapp.dto.Charge;
import com.example.legacyapp.dto.Charges;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author Marcin Grzejszczak
 */
// automatically pollutes the context!
@Component
@Primary
public class TheStub implements CustomerRentalHistoryManager {
	@Override public Charges listAllCharges() {
		Charges charges = new Charges();
		charges.getCharges().add(charge("a"));
		charges.getCharges().add(charge("b"));
		charges.getCharges().add(charge("c"));
		return charges;
	}

	Charge charge(String customer) {
		Charge charge = new Charge();
		charge.setAmount(100L);
		charge.setCaptured(true);
		charge.setCustomer(customer);
		return charge;
	}
}
