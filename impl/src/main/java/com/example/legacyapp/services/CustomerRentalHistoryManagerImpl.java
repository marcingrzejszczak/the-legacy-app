package com.example.legacyapp.services;

import java.util.HashMap;
import java.util.Map;

import com.example.legacyapp.dto.Charges;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.ChargeCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Everything has to be public due to wrong packaging
 *
 * @author Marcin Grzejszczak
 */
@Service
public class CustomerRentalHistoryManagerImpl implements CustomerRentalHistoryManager {

	@Value("${api.url:}") String url;

	@Override
	public Charges listAllCharges(String name) {
		Stripe.apiKey = "sk_test_BQokikJOvBiI2HlWgH4olfQ2";
		if (StringUtils.hasText(url)) {
			Stripe.overrideApiBase(url);
		}
		Map<String, Object> chargeParams = new HashMap<>();
		chargeParams.put("limit", 25);
		try {
			return convertTheirChargesToOurs(chargeParams);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Charges convertTheirChargesToOurs(Map<String, Object> chargeParams)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		ChargeCollection list = Charge.list(chargeParams);
		Charges charges = new Charges();
		for (Charge charge : list.getData()) {
			charges.getCharges().add(
					new com.example.legacyapp.dto.Charge(charge.getCustomer(), charge.getAmount(), charge.getCaptured())
			);
		}
		return charges;
	}
}
