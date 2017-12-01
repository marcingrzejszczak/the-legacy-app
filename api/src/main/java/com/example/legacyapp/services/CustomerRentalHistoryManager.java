package com.example.legacyapp.services;

import com.example.legacyapp.dto.Charges;

/**
 * @author Marcin Grzejszczak
 */
public interface CustomerRentalHistoryManager {
	Charges listAllCharges(String name);
}
