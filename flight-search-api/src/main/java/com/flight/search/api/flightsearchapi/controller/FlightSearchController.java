package com.flight.search.api.flightsearchapi.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flight.search.api.flightsearchapi.bean.RequestFlightBean;
import com.flight.search.api.flightsearchapi.bean.ResponseFlightBean;
import com.flight.search.api.flightsearchapi.service.FlightService;



@RestController
@RequestMapping("/api/v1/")
public class FlightSearchController {

	@Autowired
	private FlightService flightService;

	/**
	 * This method is responsible for giving the details of all the flight
	 * 
	 * @return
	 */
	@GetMapping("/flights")
	public ResponseEntity<List<ResponseFlightBean>> getAllFlight() {
		return ResponseEntity.ok(flightService.getAllFlight());
	}

	/**
	 * @param flightNumber
	 * 
	 *                     This method is responsible for giving the details of
	 *                     flight based on flight number , else it throw an
	 *                     exception of flight not found exception
	 * 
	 * @return
	 */
	@GetMapping("/flights/{flightNumber}")
	public ResponseEntity<ResponseFlightBean> getFlightByFlightNumber(@PathVariable String flightNumber) {
		return ResponseEntity.ok(flightService.getFlightByFlightNumber(flightNumber));
	}

	/**
	 * @param origin
	 * @param destination
	 * 
	 *                    This method gives the list of flight based on origin
	 * 
	 * @return
	 */
	@GetMapping("/flights/{origin}/{destination}")
	public ResponseEntity<List<ResponseFlightBean>> getFlightByOriginAndDest(@PathVariable String origin,
			@PathVariable String destination) {
		return ResponseEntity.ok(flightService.getFlightByOriginAndDest(origin, destination));
	}

	/**
	 * @param flight
	 * 
	 *               This method is responsible for creating a record of flight in
	 *               database
	 * 
	 */
	@PostMapping("/flights/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveFlight(@Valid @RequestBody RequestFlightBean flight) {
		flightService.saveFlight(flight);
	}

	/**
	 * @param flightNumber
	 * @param flight
	 * 
	 *                     This method update the details of flight based on flight
	 *                     number
	 * 
	 * @return
	 */
	@PutMapping("/flights/update/{flightNumber}")
	public ResponseEntity<ResponseFlightBean> updateFlight(@PathVariable String flightNumber,
			@Valid @RequestBody RequestFlightBean flight) {
		return ResponseEntity.ok(flightService.updateFlight(flight, flightNumber));
	}

	/**
	 * @param map
	 * @param flightNumber
	 * 
	 *                     This method patches the details of flight based on flight
	 *                     number else throw an exception if flight not found
	 * 
	 * @return
	 */
	@PatchMapping("flights/patch/{flightNumber}")
	public ResponseEntity<ResponseFlightBean> patchFlight(@RequestBody Map<String, String> map,
			@PathVariable String flightNumber) {
		return ResponseEntity.ok(flightService.patchFlight(map, flightNumber));
	}

	/**
	 * @param flightNumber
	 * 
	 *                     This method delete the details of flight based on flight
	 *                     number
	 * 
	 */
	@DeleteMapping("/flights/delete/{flightNumber}")
	public ResponseEntity<String> deleteFlight(@PathVariable String flightNumber) {
		flightService.deleteFlight(flightNumber);
		return ResponseEntity.ok("Flight with flight number : " + flightNumber + " deleted successfully");
	}

	/**
	 * This method delete the details of all the flight
	 * 
	 */
	@DeleteMapping("/flights/delete")
	public ResponseEntity<String> deleteAllFlight() {
		flightService.deleteAll();
		return ResponseEntity.ok("All flight deleted");
	}

}
