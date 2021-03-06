package com.crud.traveller.domain;

import com.crud.traveller.entity.Flights;
import com.crud.traveller.entity.Hotel;
import com.crud.traveller.entity.User;
import com.crud.traveller.entity.Weather;
import com.crud.traveller.patterns.observer.Observer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExcursionDto {
    private Long excursionId;
    private String destination;
    private double price;
    private LocalDate departureDate;
    private List<String> specialOffer;
    private List<Observer> observers;
    private List<Hotel> hotels = new ArrayList<> ();
    private List<Flights> flights = new ArrayList<> ();
    private List<Weather> weather;
    private User user;
    
}
