package com.iceman.formula_one.exception;

/**
 * The Exception is thrown when a racer in the game makes a wrong movement on the race track
 */
public class DriveException extends Exception {

	private static final long serialVersionUID = -7504765408766469087L;

	public DriveException(final String pMessage) {
		super(pMessage);
	}

}
