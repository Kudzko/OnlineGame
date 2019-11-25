package com.iceman.formula_one.service.game.impl;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.iceman.formula_one.model.track.utility.RaceTrackUtility;
import com.iceman.formula_one.model.car.Car;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.iceman.formula_one.exception.DriveException;
import com.iceman.formula_one.model.Direction;
import com.iceman.formula_one.model.track.RaceTrack;
import com.iceman.formula_one.model.coordinates.Coordinates;
import com.iceman.formula_one.model.web.request.ActionRequest;
import com.iceman.formula_one.service.game.RaceTrackService;

/**
 * A class in which implemented main game logic.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Getter
@Setter
public class RaceTrackServiceImpl implements RaceTrackService {


    @Value("${raceTrack.dimension.x.default}")
    private int dimensionXDefault;

    @Value("${raceTrack.dimension.y.default}")
    private int dimensionYDefault;

	@Value("${raceTrack.notifications.trackLimit}")
	private String TRACK_LIMIT_MESSAGE;

	@Value("${raceTrack.notifications.sector.occupied}")
	private String SECTOR_OCCUPIED;

	private RaceTrack raceTrack;


	@PostConstruct
	public void init() {
		raceTrack = new RaceTrack(this.dimensionXDefault, this.dimensionYDefault);
		raceTrack.getCars().addAll(RaceTrackUtility.getStartingLine());
	}

	@Override
	public Car processAction(ActionRequest actionRequest) throws DriveException {
		Car car = findVehicleById(actionRequest.getVehicleUuid());

		switch (actionRequest.getAction()) {
		case DRIVE:
			move(car);
			break;
		case CLOCKWISE_MANEUVER:
			turn(true, car);
			break;
		case COUNTERCLOCKWISE_MANEUVER:
			turn(false, car);
			break;
		default:
			break;
		}
		return car;
	}

	private void move(Car car) throws DriveException {
		int stepPoints = car.getSpeed();
		int x = car.getCoordinates().getDimensionX();
		int y = car.getCoordinates().getDimensionY();

		switch (car.getDirection()) {
		case RIGHT:
			x += stepPoints;
			break;
			case FORWARD:
			y += stepPoints;
			break;
			case BACKWARD:
			y -= stepPoints;
			break;
			case LEFT:
			x -= stepPoints;
			break;
		default:
			break;
		}

		checkCoordinates(x, y);

		car.getCoordinates().setDimensionX(x);
		car.getCoordinates().setDimensionY(y);
	}


	private void checkCoordinates(int x, int y) throws DriveException {
		checkIfDimensionsAreValid(x, y);
		checkIfDimensionsAreUnique(x, y);
	}

	private void checkIfDimensionsAreValid(int x, int y) throws DriveException {
		if ((x < 0 || x >= this.dimensionXDefault) || (y < 0 || y >= this.dimensionYDefault))
		{
			throw new DriveException(TRACK_LIMIT_MESSAGE);
		}
	}

	private void checkIfDimensionsAreUnique(int x, int y) throws DriveException {
		boolean unique = this.raceTrack.getCars().stream().noneMatch(vehicle -> {
			Coordinates coordinates = vehicle.getCoordinates();
			return coordinates.getDimensionX() == x && coordinates.getDimensionY() == y;
		});

		if (!unique) {
			throw new DriveException(SECTOR_OCCUPIED);
		}
	}

	private void turn(boolean isClockwise, Car car) {
		Direction[] directions = Direction.values();

		int index = car.getDirection().ordinal();
		if (isClockwise) {
			index++;
			if (index > directions.length - 1) {
				index = 0;
			}
		} else {
			index--;
			if (index < 0) {
				index = directions.length - 1;
			}
		}
		car.setDirection(directions[index]);
	}

	private Car findVehicleById(String vehicleId) {
		return this.raceTrack.getCars().stream().filter(vehicle -> vehicleId.equals(vehicle.getUuid()))
				.collect(toSingleton());
	}

	private static <T> Collector<T, ?, T> toSingleton() {
		return Collectors.collectingAndThen(Collectors.toList(), list -> {
			if (list.size() != 1) {
				throw new IllegalStateException();
			}
			return list.get(0);
		});
	}

}
