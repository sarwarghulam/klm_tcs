package com.flight.search.api.flightsearchapi.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RequestFlightBean {

	@NotBlank(message = "Flight Number is mandatory")
	private String flightNumber;
	@NotEmpty(message = "Origin should not be empty or null")
	private String origin;
	@NotEmpty(message = "Destination should not be empty or null")
	private String destination;

	/**
	 * total duration of flight in minutes
	 */
	@NotNull(message = "Duration should be greater than zero")
	private Integer duration;

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
}
