package com.example.legacyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Marcin Grzejszczak
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Charge {
	private String customer;
	private Long amount;
	private boolean captured;
}
