package com.project.flight_Flight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FlightFlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightFlightApplication.class, args);
	}

}
