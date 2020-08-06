package com.crud.traveller.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="HOTELS")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelId;
    private String destination;
    private String hotelName;
    private String roomType;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private int guestsNumber;
    private double price;
    @ManyToOne
    @JoinColumn(name = "EXCURSION_ID")
    private Excursion excursion;
}
