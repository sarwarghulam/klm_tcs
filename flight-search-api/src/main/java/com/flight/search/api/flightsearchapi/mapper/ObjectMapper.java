package com.flight.search.api.flightsearchapi.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flight.search.api.flightsearchapi.bean.RequestFlightBean;
import com.flight.search.api.flightsearchapi.bean.ResponseFlightBean;
import com.flight.search.api.flightsearchapi.entity.Flight;

@Component
public class ObjectMapper {
	
	@Autowired
	private ModelMapper mapper;

	/**
	 * @param request
	 * 
	 *                Map RequestFlightBean to Flight object
	 * 
	 * @return
	 */
	public Flight mapRequestToEntity(RequestFlightBean request) {
		return mapper.map(request, Flight.class);
	}

	/**
	 * @param entity
	 * 
	 *               Map Flight toResponseFlightBean object
	 * 
	 * @return
	 */
	public ResponseFlightBean mapEntityToResponse(Flight entity) {
		return mapper.map(entity, ResponseFlightBean.class);
	}

	/**
	 * @param entity
	 * 
	 *               Map List of flight to Response flight bean obj
	 * 
	 * @return
	 */
	public List<ResponseFlightBean> mapEntityToResponse(List<Flight> entity) {
		List<ResponseFlightBean> list = new ArrayList<>();
		entity.forEach((ent) -> {
			list.add(mapper.map(ent, ResponseFlightBean.class));
		});
		return list;
	}

}
