package com.iceman.formula_one.model.coordinates;

import lombok.*;

/**
 * A class that represents coordinates in two-dimensional space
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Coordinates {

    private int dimensionX;
	private int dimensionY;

	public Coordinates(int dimensionX, int dimensionY) {
		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;
	}

}
