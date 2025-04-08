package com.project.flight_Flight.service;

import com.project.flight_Flight.model.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService{
    @Override
    public Flight addFlight(Flight flight) {
        return null;
    }

    @Override
    public Flight updateFlight(Long id, Flight flight) throws Exception {
        return null;
    }

    @Override
    public void deleteFlight(Long id) {

    }

    @Override
    public Flight getFlightById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Flight> getAllFlights() {
        return null;
    }

    @Override
    public List<Flight> searchFlights(String from, String to) {
        return null;
    }
}
