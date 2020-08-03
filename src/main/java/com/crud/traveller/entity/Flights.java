package com.crud.traveller.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Flights {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightId;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private double price;
    @ManyToOne
    private Excursion excursion;
}
