package com.crud.traveller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String userName;
    private String userKey;

    @OneToMany(targetEntity = Excursion.class,
                mappedBy = "user",
                cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    private List<Excursion> excursion = new ArrayList<> ();

    @OneToMany(targetEntity = CurrencyExchange.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<CurrencyExchange> currencyExchange;

   /* @OneToMany(targetEntity = Weather.class,
                mappedBy = "user",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    private List<Weather> weatherForecast;*/
}