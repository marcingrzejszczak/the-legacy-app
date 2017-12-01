package com.example.legacyapp.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Marcin Grzejszczak
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Charges {
	private List<Charge> charges = new ArrayList<>();
}
