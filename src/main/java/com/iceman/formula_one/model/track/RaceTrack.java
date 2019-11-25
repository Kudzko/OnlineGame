package com.iceman.formula_one.model.track;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import com.iceman.formula_one.model.car.Car;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * A class that represents a  race track. On a raceTrack we have xDimension and yDimension sectors on which cars can drive.
 * Contains an information about all the cars on the track.
 */
@Getter
@Setter
@EqualsAndHashCode
public class RaceTrack {

    private String uuid;
	private int xDimension;
	private int yDimension;

	private Set<Car> cars = new HashSet<>();

	public RaceTrack(int xDimension, int yDimension) {
		this.uuid = UUID.randomUUID().toString();
		this.xDimension = xDimension;
		this.yDimension = yDimension;
	}

}
