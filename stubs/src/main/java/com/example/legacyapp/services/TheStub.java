package com.example.legacyapp.services;

import java.util.Arrays;

import com.google.gson.Gson;
import com.stripe.model.Charge;
import com.stripe.model.ChargeCollection;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author Marcin Grzejszczak
 */
// automatically pollutes the context!
@Component
@Primary
public class TheStub implements CustomerRentalHistoryManager {
	@Override public String listAllCharges() {
		ChargeCollection chargeCollection = new ChargeCollection();
		chargeCollection.setData(
				Arrays.asList(charge("a"),
						charge("b"),
						charge("c"))
		);
		return new Gson().toJson(chargeCollection);
	}

	Charge charge(String customer) {
		Charge charge = new Charge();
		charge.setAmount(100L);
		charge.setCaptured(true);
		charge.setCustomer(customer);
		return charge;
	}
}
