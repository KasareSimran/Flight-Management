package com.project.flight_Flight.service;

import com.project.flight_Flight.model.Flight;
import com.project.flight_Flight.repository.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlightServiceImpl implements FlightService{


    @Autowired
    private FlightRepo flightRepo;


    @Override
    public Flight addFlight(Flight flight) {
        return flightRepo.save(flight);
    }

    @Override
    public Flight updateFlight(Long id, Flight flight) throws Exception {
        Flight existing = flightRepo.findById(id)
                .orElseThrow(() -> new Exception("Flight not found"));

        existing.setAirlineName(flight.getAirlineName());
        existing.setFlightNumber(flight.getFlightNumber());
        existing.setFromLocation(flight.getFromLocation());
        existing.setToLocation(flight.getToLocation());
        existing.setDepartureTime(flight.getDepartureTime());
        existing.setArrivalTime(flight.getArrivalTime());
        existing.setTotalSeats(flight.getTotalSeats());
        existing.setAvailableSeats(flight.getAvailableSeats());
        existing.setPrice(flight.getPrice());

        return flightRepo.save(existing);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepo.deleteById(id);

    }

    @Override
    public Flight getFlightById(Long id) throws Exception {
        return flightRepo.findById(id).orElseThrow(()->new Exception("Flight not found"));
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepo.findAll();
    }

    @Override
    public List<Flight> searchFlights(String fromLocation, String toLocation) {
        return flightRepo.searchFlights(fromLocation, toLocation);
    }
}
