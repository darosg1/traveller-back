package com.crud.traveller.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDto {
    private Long hotelId;
    private String destination;
    private String hotelName;
    private String roomType;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private int guestsNumber;
    private double price;
}
