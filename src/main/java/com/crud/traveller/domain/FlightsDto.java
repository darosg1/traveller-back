package com.crud.traveller.domain;

import com.crud.traveller.entity.Excursion;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightsDto {
    private Long flightId;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private double price;
    private Excursion excursion;
}