package com.crud.traveller.entity;

import lombok.*;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="EXCURSIONS")
@Component
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long excursionId;
    private String destination;
    private double price;
    private LocalDate departureDate;

    @OneToMany(targetEntity = Hotel.class,
            mappedBy = "excursion",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Hotel> hotels = new ArrayList<> ();

    @OneToMany(targetEntity = Flights.class,
            mappedBy = "excursion",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Flights> flights = new ArrayList<> ();

    @Transient
    private Weather weather;

    @ManyToOne
    private User user;
}
