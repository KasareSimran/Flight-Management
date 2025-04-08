package com.project.flight_Flight.service;

import com.project.flight_Flight.model.Flight;

import java.util.List;

public interface FlightService {

    Flight addFlight(Flight flight);
    Flight updateFlight(Long id,Flight flight) throws Exception;
    void deleteFlight(Long id);
    Flight getFlightById(Long id) throws Exception;
    List<Flight> getAllFlights();
    List<Flight> searchFlights(String fromLocation, String toLocation);
}
