package com.project.flight_Flight.controller;


import com.project.flight_Flight.model.Flight;
import com.project.flight_Flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;


    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody  Flight flight){
        return ResponseEntity.ok(flightService.addFlight(flight));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight)throws Exception{
        return ResponseEntity.ok(flightService.updateFlight(id,flight));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id){
        flightService.deleteFlight(id);
        return ResponseEntity.ok("Flight deleted successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id)throws Exception{
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlight(){
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlight(@RequestParam String fromLocation, @RequestParam String toLocation){
        return ResponseEntity.ok(flightService.searchFlights(fromLocation,toLocation));
    }

    @GetMapping("/search-by-date")
    public List<Flight> getFlightsByDateRange(
            @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,

            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        return flightService.getFlightsByDateRange(start, end);
    }




}
