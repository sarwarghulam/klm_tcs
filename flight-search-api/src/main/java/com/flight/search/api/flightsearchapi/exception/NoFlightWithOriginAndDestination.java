package com.flight.search.api.flightsearchapi.exception;

/**
 * This class is a custom exception for the case of flight not found based on
 * origin and destination
 * 
 */
public class NoFlightWithOriginAndDestination extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoFlightWithOriginAndDestination(String origin, String destination) {
		super("No Flight with Origin " + origin + " and Destination " + destination + " is available");

	}

	public NoFlightWithOriginAndDestination() {
		super("No Flight Available");

	}

}
