package com.crud.traveller.domain;

import com.crud.traveller.entity.Excursion;
import lombok.*;
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
    private Excursion excursion;
}
