package com.flight.search.api.flightsearchapi.controller;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flight.search.api.flightsearchapi.bean.RequestFlightBean;
import com.flight.search.api.flightsearchapi.bean.ResponseFlightBean;
import com.flight.search.api.flightsearchapi.service.FlightService;

import junit.framework.Assert;

@ExtendWith(MockitoExtension.class)
public class FlightSearchControllerTest {

	@Mock
	private FlightService flightService;

	@InjectMocks
	private FlightSearchController flightSearchController;

	private ResponseFlightBean responseFlightBean;

	@BeforeEach
	void setUp() {
		responseFlightBean = new ResponseFlightBean();
		responseFlightBean.setFlightNumber("AZ01");

	}

	@Test
	public void getAllFlight() {
		Mockito.when(flightService.getAllFlight()).thenReturn(new ArrayList<>());

		Assert.assertNotNull(flightSearchController.getAllFlight());
	}

	@Test
	public void getFlightByFlightNumber() {
		Mockito.when(flightService.getFlightByFlightNumber(any())).thenReturn(this.responseFlightBean);
		Assert.assertEquals(flightSearchController.getFlightByFlightNumber("AZ01").getBody().getFlightNumber(), "AZ01");
	}

	@Test
	public void getFlightByOriginAndDest() {
		Mockito.when(flightService.getFlightByOriginAndDest(any(), any())).thenReturn(new ArrayList<>());
		Assert.assertNotNull(flightSearchController.getFlightByOriginAndDest("", ""));
	}

	@Test
	public void saveFlight() {
		Mockito.doNothing().when(flightService).saveFlight(any());
		flightSearchController.saveFlight(new RequestFlightBean());
		Mockito.verify(flightService).saveFlight(any());
	}

	@Test
	public void updateFlight() {
		Mockito.when(flightService.updateFlight(any(), any())).thenReturn(this.responseFlightBean);
		Assert.assertEquals(
				flightSearchController.updateFlight("", new RequestFlightBean()).getBody().getFlightNumber(), "AZ01");
	}

	@Test
	public void patchFlight() {
		Mockito.when(flightService.patchFlight(any(), any())).thenReturn(this.responseFlightBean);
		Assert.assertEquals(flightSearchController.patchFlight(new HashMap<>(), "").getBody().getFlightNumber(),
				"AZ01");
	}

	@Test
	public void deleteFlight() {
		Mockito.doNothing().when(flightService).deleteFlight(any());
		flightSearchController.deleteFlight("AZ01");
		Mockito.verify(flightService).deleteFlight(any());
	}

	@Test
	public void deleteAllFlight() {
		Mockito.doNothing().when(flightService).deleteAll();
		flightSearchController.deleteAllFlight();
		Mockito.verify(flightService).deleteAll();
	}

}
