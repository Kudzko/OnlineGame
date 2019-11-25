package com.iceman.formula_one.model.car;

import com.iceman.formula_one.model.Direction;
import com.iceman.formula_one.model.Type;
import com.iceman.formula_one.model.coordinates.Coordinates;
import com.iceman.formula_one.model.racer.Racer;

/**
 * A class that represents the slowest f_1 car - Williams
 */
public class Williams_FW_42 extends Car {

    private static final int WILLIAMS_DEFAULT_SPEED = 1;

    public Williams_FW_42() {
        super(WILLIAMS_DEFAULT_SPEED);
    }

    public Williams_FW_42(Type type, Racer racer, Coordinates coordinates, Direction direction) {
        super(WILLIAMS_DEFAULT_SPEED, type, racer, coordinates, direction);
    }

}
