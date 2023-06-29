package com.flight.search.api.flightsearchapi.controller.advice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flight.search.api.flightsearchapi.exception.FlightNotFoundException;
import com.flight.search.api.flightsearchapi.exception.NoFlightWithOriginAndDestination;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * This method changes the error response in custom format
	 */
	@ExceptionHandler({ FlightNotFoundException.class, NoFlightWithOriginAndDestination.class })
	protected ResponseEntity<Object> handleException(Exception ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new java.util.Date());
		body.put("status", HttpStatus.NOT_FOUND);
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		body.put("errors", errors);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
}
