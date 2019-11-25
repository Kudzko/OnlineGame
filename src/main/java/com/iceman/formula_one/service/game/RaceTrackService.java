package com.iceman.formula_one.service.game;

import com.iceman.formula_one.exception.DriveException;
import com.iceman.formula_one.model.car.Car;
import com.iceman.formula_one.model.web.request.ActionRequest;

/**
 * An interface in which child should be implemented main game logic.
 */
public interface RaceTrackService {

	/**
	 * A method that handles players actions
	 * @param actionRequest - racer action to be processed
	 * @return car with an updated state
	 * @throws DriveException if the car makes an invalid move
	 */
	Car processAction(ActionRequest actionRequest) throws DriveException;
}
