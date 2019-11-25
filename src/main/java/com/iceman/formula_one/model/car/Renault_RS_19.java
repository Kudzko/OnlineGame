package com.iceman.formula_one.model.car;

import com.iceman.formula_one.model.Direction;
import com.iceman.formula_one.model.Type;
import com.iceman.formula_one.model.coordinates.Coordinates;
import com.iceman.formula_one.model.racer.Racer;

/**
 * A class that represents common f_1 car - Renault
 */
public class Renault_RS_19 extends Car {

	private static final int RENAULT_DEFAULT_SPEED = 2;

	public Renault_RS_19() {
		super(RENAULT_DEFAULT_SPEED);
	}

	public Renault_RS_19(Type type, Racer racer, Coordinates coordinates, Direction direction){
		super(RENAULT_DEFAULT_SPEED, type, racer, coordinates, direction);
	}

}
