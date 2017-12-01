package com.example.legacyapp.services;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.ChargeCollection;
import org.springframework.stereotype.Service;

/**
 * Everything has to be public due to wrong packaging
 *
 * @author Marcin Grzejszczak
 */
@Service
public class CustomerRentalHistoryManagerImpl implements CustomerRentalHistoryManager {

	public String listAllCharges() {
		Stripe.apiKey = "sk_test_BQokikJOvBiI2HlWgH4olfQ2";
		Map<String, Object> chargeParams = new HashMap<>();
		chargeParams.put("limit", 3);
		try {
			return new Gson().toJson(Charge.list(chargeParams));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
