package com.iceman.formula_one.model.web.response;

import com.iceman.formula_one.model.car.Car;
import lombok.Getter;
import lombok.Setter;

/**
 * A class that represents a response sending to the client
 */
@Getter
@Setter
public class ActionResponse {

    private Status status;
	private Car updatedCar;
	private String message;


}
