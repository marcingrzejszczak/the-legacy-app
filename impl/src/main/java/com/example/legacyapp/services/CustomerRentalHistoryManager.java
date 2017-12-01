package com.example.legacyapp.services;

import com.stripe.model.ChargeCollection;

/**
 * @author Marcin Grzejszczak
 */
public interface CustomerRentalHistoryManager {
	String listAllCharges();
}
