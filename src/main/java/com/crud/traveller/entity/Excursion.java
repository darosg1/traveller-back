package com.crud.traveller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Excursion {
    private String destination;
    private double price;
    private LocalDate departureDate;
    private List<Hotel> hotels = new ArrayList<> ();
    private List<Flights> flights = new ArrayList<> ();
    private Weather weather;
}
