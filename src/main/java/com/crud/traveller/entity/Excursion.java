package com.crud.traveller.entity;

import com.crud.traveller.patterns.observer.Observable;
import com.crud.traveller.patterns.observer.Observer;
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
public class Excursion implements Observable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long excursionId;
    private String destination;
    private double price;
    private LocalDate departureDate;
    @Transient
    private List<String> specialOffer;

    @Transient
    private List<Observer> observers;

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
    private List<Weather> weather;

    @ManyToOne
    private User user;

    public void excursionInfo(String offerInfo){
        specialOffer.add(offerInfo);
        notifyObservers ();
    }
    @Override
    public void registerObserver (Observer observer){
        observers.add (observer);
    }

    @Override
    public void notifyObservers (){
        for(Observer observer:observers)
            observer.update (this);
    }

    @Override
    public void removeObserver (Observer observer){
        observers.remove (observer);
    }
}
