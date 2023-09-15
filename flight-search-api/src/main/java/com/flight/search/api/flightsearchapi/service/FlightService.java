package com.flight.search.api.flightsearchapi.service;

import java.util.List;
import java.util.Map;

import com.flight.search.api.flightsearchapi.bean.RequestFlightBean;
import com.flight.search.api.flightsearchapi.bean.ResponseFlightBean;


public interface FlightService {

	public List<ResponseFlightBean> getAllFlight();

	public ResponseFlightBean getFlightByFlightNumber(String flightNumber);

	public List<ResponseFlightBean> getFlightByOriginAndDest(String origin, String destination);

	public void saveFlight(RequestFlightBean flight);

	public ResponseFlightBean updateFlight(RequestFlightBean flight, String flightNumber);

	public ResponseFlightBean patchFlight(Map<String, String> map, String flightNumber);

	public void deleteFlight(String flightNumber);

	public void deleteAll();
}
