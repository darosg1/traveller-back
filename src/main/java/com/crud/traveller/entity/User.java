package com.crud.traveller.entity;

import com.crud.traveller.patterns.observer.Observer;
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
public class User implements Observer {
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

    @Transient
    private List<Currency> currency;

    @Transient
    private List<Weather> weather;

    @Override
    public void update (Excursion excursion){
        System.out.println (userName + " New special offer for" + excursion.getSpecialOffer ());
    }
}