package com.project.flight_Flight.repository;

import com.project.flight_Flight.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepo extends JpaRepository<Flight,Long> {
    List<Flight> findByFromAndTo(String from ,String to);
}
