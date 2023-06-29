package com.flight.search.api.flightsearchapi.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.search.api.flightsearchapi.bean.RequestFlightBean;
import com.flight.search.api.flightsearchapi.bean.ResponseFlightBean;
import com.flight.search.api.flightsearchapi.entity.Flight;
import com.flight.search.api.flightsearchapi.exception.FlightNotFoundException;
import com.flight.search.api.flightsearchapi.exception.NoFlightWithOriginAndDestination;
import com.flight.search.api.flightsearchapi.mapper.ObjectMapper;
import com.flight.search.api.flightsearchapi.repository.FlightRepository;
import com.flight.search.api.flightsearchapi.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private ObjectMapper objMapper;

	/**
	 * This method is responsible for giving the details of all the flight
	 * 
	 * @return
	 */
	@Override
	public List<ResponseFlightBean> getAllFlight() {
		return objMapper.mapEntityToResponse(flightRepository.findAll());
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
	@Override
	public ResponseFlightBean getFlightByFlightNumber(String flightNumber) {
		return objMapper.mapEntityToResponse(
				flightRepository.findById(flightNumber).orElseThrow(() -> new FlightNotFoundException(flightNumber)));
	}

	/**
	 * @param origin
	 * @param destination
	 * 
	 *                    This method gives the list of flight based on origin
	 * 
	 * @return
	 */
	@Override
	public List<ResponseFlightBean> getFlightByOriginAndDest(String origin, String destination) {
		List<Flight> listOfFlight = flightRepository.findByOriginAndDestination(origin, destination);
		if (listOfFlight == null || listOfFlight.isEmpty())
			throw new NoFlightWithOriginAndDestination(origin, destination);
		else
			return objMapper.mapEntityToResponse(listOfFlight);
	}

	/**
	 * @param flight
	 * 
	 *               This method is responsible for creating a record of flight in
	 *               database
	 * 
	 */
	@Override
	public void saveFlight(RequestFlightBean flight) {
		flightRepository.save(objMapper.mapRequestToEntity(flight));
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
	@Override
	public ResponseFlightBean updateFlight(RequestFlightBean flight, String flightNumber) {
		return objMapper.mapEntityToResponse(flightRepository.save(objMapper.mapRequestToEntity(flight)));
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
	@Override
	public ResponseFlightBean patchFlight(Map<String, String> map, String flightNumber) throws FlightNotFoundException {
		Optional<Flight> fl = flightRepository.findById(flightNumber);
		if(fl != null && fl.isPresent()) {
			Flight flight = fl.get();
			map.forEach((key, value) -> {
				switch (key) {
				case "origin":
					flight.setOrigin(value);
					break;
				case "destination":
					flight.setDestination(value);
					break;
				case "duration":
					flight.setDuration(Integer.parseInt(value));
					break;
				default:
				}
			});
			flightRepository.save(flight);
			return objMapper.mapEntityToResponse(flight);
		}else {
			throw new FlightNotFoundException();
		}
	}

	/**
	 * @param flightNumber
	 * 
	 *                     This method delete the details of flight based on flight
	 *                     number
	 * 
	 */
	@Override
	public void deleteFlight(String flightNumber) {
		flightRepository.deleteById(flightNumber);
	}

	/**
	 * This method delete the details of all the flight
	 * 
	 */
	@Override
	public void deleteAll() {
		flightRepository.deleteAll();
	}

}
