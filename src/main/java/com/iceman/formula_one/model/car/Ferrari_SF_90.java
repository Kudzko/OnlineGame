package com.iceman.formula_one.model.car;

import com.iceman.formula_one.model.Direction;
import com.iceman.formula_one.model.Type;
import com.iceman.formula_one.model.coordinates.Coordinates;
import com.iceman.formula_one.model.racer.Racer;

/**
 * A class that represents the fastest f_1 car - Ferrari
 */
public class Ferrari_SF_90 extends Car {

	private static final int FERRARI_DEFAULT_SPEED = 3;

	public Ferrari_SF_90() {
		super(FERRARI_DEFAULT_SPEED);
	}

	public Ferrari_SF_90(Type type, Racer racer, Coordinates coordinates, Direction direction){
		super(FERRARI_DEFAULT_SPEED, type,  racer, coordinates, direction);
	}

}
