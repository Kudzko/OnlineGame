package com.iceman.formula_one.model.car;

import java.util.UUID;

import com.iceman.formula_one.model.Direction;
import com.iceman.formula_one.model.Type;
import com.iceman.formula_one.model.coordinates.Coordinates;
import com.iceman.formula_one.model.racer.Racer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * An abstract class that represents basic f_1 car
 */
@Getter
@Setter
@EqualsAndHashCode
public abstract class Car  {

    private String uuid;
    private Type type;
	private Racer racer;
	private Coordinates coordinates;
	private Direction direction;
	private int speed;

	protected Car(int speed) {
		this.uuid = UUID.randomUUID().toString();
		this.speed = speed;
	}

	protected Car(int speed, Type type, Racer racer, Coordinates coordinates, Direction direction) {
		this(speed);
		this.type =type;
		this.racer = racer;
		this.coordinates = coordinates;
		this.direction = direction;
	}

}
