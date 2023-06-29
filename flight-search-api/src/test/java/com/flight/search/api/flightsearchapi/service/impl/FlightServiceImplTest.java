package com.flight.search.api.flightsearchapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flight.search.api.flightsearchapi.bean.RequestFlightBean;
import com.flight.search.api.flightsearchapi.bean.ResponseFlightBean;
import com.flight.search.api.flightsearchapi.entity.Flight;
import com.flight.search.api.flightsearchapi.exception.FlightNotFoundException;
import com.flight.search.api.flightsearchapi.exception.NoFlightWithOriginAndDestination;
import com.flight.search.api.flightsearchapi.mapper.ObjectMapper;
import com.flight.search.api.flightsearchapi.repository.FlightRepository;

import junit.framework.Assert;

@ExtendWith(MockitoExtension.class)
public class FlightServiceImplTest {

	@Mock
	private FlightRepository flightRepository;

	@Mock
	private ObjectMapper objectMapper;

	@InjectMocks
	private FlightServiceImpl flightService;

	private Flight flight;

	private ResponseFlightBean responseFlightBean;

	@BeforeEach
	void setUp() {
		flight = new Flight();
		responseFlightBean = new ResponseFlightBean();
		responseFlightBean.setDestination("mumbai");
	}

	public void getAllFlight() {
		Mockito.when(flightRepository.findAll()).thenReturn(new ArrayList<>());
		Mockito.when(objectMapper.mapEntityToResponse(any(List.class))).thenReturn(new ArrayList<>());
		flightService.getAllFlight();
		Mockito.verify(flightRepository).findAll();
	}

	public void getFlightByFlightNumber() {
		Mockito.when(flightRepository.findById(any())).thenReturn(Optional.of(this.flight));
		Mockito.when(objectMapper.mapEntityToResponse(any(Flight.class))).thenReturn(this.responseFlightBean);
		flightService.getFlightByFlightNumber("");
		Mockito.verify(flightRepository).findById(any());
		Mockito.when(flightRepository.findById(any())).thenReturn(Optional.of(null));
		assertThrows(FlightNotFoundException.class, () -> flightService.getFlightByFlightNumber(""));
	}

	@Test
	public void getFlightByOriginAndDest() {
		Mockito.when(flightRepository.findByOriginAndDestination(any(), any())).thenReturn(new ArrayList<>());
		Mockito.when(objectMapper.mapEntityToResponse(any(List.class))).thenReturn(new ArrayList<>());
		assertThrows(NoFlightWithOriginAndDestination.class, () -> flightService.getFlightByOriginAndDest("", ""));
		Mockito.verify(flightRepository).findByOriginAndDestination(any(), any());
		List<Flight> list = new ArrayList<>();
		list.add(new Flight());
		Mockito.when(flightRepository.findByOriginAndDestination(any(), any())).thenReturn(list);
		flightService.getFlightByOriginAndDest("", "");
		Mockito.verify(flightRepository, times(2)).findByOriginAndDestination(any(), any());
	}

	@Test
	public void saveFlight() {
		Mockito.when(flightRepository.save(any())).thenReturn(this.flight);
		Mockito.when(objectMapper.mapRequestToEntity(any(RequestFlightBean.class))).thenReturn(this.flight);
		flightService.saveFlight(new RequestFlightBean());
		Mockito.verify(flightRepository).save(any());
	}

	@Test
	public void updateFlight() {
		Mockito.when(flightRepository.save(any())).thenReturn(this.flight);
		Mockito.when(objectMapper.mapEntityToResponse(any(Flight.class))).thenReturn(this.responseFlightBean);
		Assert.assertEquals(flightService.updateFlight(new RequestFlightBean(), "AZ01").getDestination(), "mumbai");

	}

	@Test
	public void patchFlight() {
		Mockito.when(flightRepository.findById(any())).thenReturn(Optional.of(this.flight));
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("origin", "delhi");
		hashMap.put("destination", "mumbai");
		Mockito.when(objectMapper.mapEntityToResponse(any(Flight.class))).thenReturn(this.responseFlightBean);

		Assert.assertEquals(flightService.patchFlight(hashMap, "AZ01").getDestination(), "mumbai");
		Mockito.when(flightRepository.findById(any())).thenReturn(null);
		assertThrows(FlightNotFoundException.class, () -> flightService.patchFlight(hashMap, "AZ01"));
	}

	@Test
	public void deleteFlight() {
		Mockito.doNothing().when(flightRepository).deleteById(any());
		flightService.deleteFlight("AZ01");
		Mockito.verify(flightRepository).deleteById(any());
	}

	@Test
	public void deleteAll() {
		Mockito.doNothing().when(flightRepository).deleteAll();
		flightService.deleteAll();
		Mockito.verify(flightRepository).deleteAll();
	}

}
