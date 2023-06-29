package com.flight.search.api.flightsearchapi.exception;

/**
 * This class is a custom exception class for the case of flight not found
 * 
 */
public class FlightNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlightNotFoundException(String flightNumber) {
		super("Flight with Flight Number " + flightNumber + " Not Found");
	}

	public FlightNotFoundException() {
		super("No Flight Found ");
	}

}
