package com.crud.traveller.entity;

import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hotel {
    private Long hotelId;
    private String destination;
    private String hotelName;
    private String roomType;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private int guestsNumber;
    private double price;
}
