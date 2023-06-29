package com.flight.search.api.flightsearchapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.search.api.flightsearchapi.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

	/**
	 * @param origin
	 * @param destination
	 * 
	 *                    This method is responsible for giving the list of flight
	 *                    based on origin and destination
	 * 
	 * @return
	 */
	List<Flight> findByOriginAndDestination(String origin, String destination);

}
