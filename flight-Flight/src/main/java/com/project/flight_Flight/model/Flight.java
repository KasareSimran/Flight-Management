package com.project.flight_Flight.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airlineName;
    private String flightNumber;

    private String fromLocation;
    private String toLocation;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private int totalSeats;
    private int availableSeats;

    private double price;





}
