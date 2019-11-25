package com.iceman.formula_one.model.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A class that represents a request sending by client
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionRequest {

	private Action action;
	private String vehicleUuid;

}
