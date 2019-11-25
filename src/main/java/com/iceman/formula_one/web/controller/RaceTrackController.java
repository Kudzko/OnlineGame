package com.iceman.formula_one.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.iceman.formula_one.exception.DriveException;
import com.iceman.formula_one.model.car.Car;
import com.iceman.formula_one.model.web.request.ActionRequest;
import com.iceman.formula_one.model.web.response.ActionResponse;
import com.iceman.formula_one.model.web.response.Status;
import com.iceman.formula_one.service.game.RaceTrackService;

/**
 * A controller-class that listens to the web socket connections, receives game events on specified URL
 * and send sends the result of processing the request to the topic that the players are subscribed
 */
@Controller
public class RaceTrackController {
	private static final String RACE_TRACK_PATH = "/raceTrack/{raceTrack}";

	private RaceTrackService raceTrackService;

	@Autowired
	public RaceTrackController(RaceTrackService raceTrackService) {
		this.raceTrackService = raceTrackService;
	}

	/**
	 * A method in which events of the game are processed
	 * @param actionRequest
	 * @return
	 */
	@MessageMapping(RACE_TRACK_PATH)
	@SendTo(RACE_TRACK_PATH)
	public ActionResponse processMessage(ActionRequest actionRequest) {
		ActionResponse result = new ActionResponse();

		Car updatedCar = null;
		try {
			updatedCar = this.raceTrackService.processAction(actionRequest);
			result.setStatus(Status.SUCCESS);
		} catch (DriveException e) {
			result.setStatus(Status.ERROR);
			result.setMessage(e.getMessage());
		}
		result.setUpdatedCar(updatedCar);

		return result;
	}
}
