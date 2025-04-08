package com.project.flight_Flight.repository;

import com.project.flight_Flight.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepo extends JpaRepository<Flight,Long> {
    List<Flight> findByFromLocationAndToLocation(String fromLocation ,String toLocation);

    @Query("SELECT f FROM Flight f WHERE f.fromLocation = :from AND f.toLocation = :to")
    List<Flight> searchFlights(@Param("from") String fromLocation, @Param("to") String toLocation);

}
