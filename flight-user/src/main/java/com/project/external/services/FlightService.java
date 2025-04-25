package com.project.external.services;


import com.project.model.Flight;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FLIGHT-FLIGHT")
public interface FlightService {

    @GetMapping("api/flights/{flightId}")
     Flight getFlight(@PathVariable("flightId") Long fightId);
}
