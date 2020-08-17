package com.crud.traveller.service;

import com.crud.traveller.entity.*;
import com.crud.traveller.patterns.observer.Observer;
import com.crud.traveller.repository.FlightsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FlightsDbServiceTest {
    @InjectMocks
    FlightsDbService flightsDbService;
    @Mock
    FlightsRepository flightsRepository;

    @Test
    public void findAllFlightsTest() {
        //Given
        List<Hotel> hotelsItaly = new ArrayList<> ();
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        List<String> specialOffer = new ArrayList<> ();
        List<Observer> observers = new ArrayList<> ();
        User user = new User ( 1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly );
        Excursion excursion = new Excursion ( 1L, "Italy", 2000.00, LocalDate.of ( 2020, 8, 20 ), specialOffer, observers, hotelsItaly, null, weatherItaly, user);
        Flights flight1 = new Flights (1L,"Krakow", "Rome", LocalDate.of ( 2020,8,30 ), LocalDate.of(2020,9,15), 250.00, excursion);
        Flights flight2 = new Flights (2L,"Krakow", "Rome", LocalDate.of ( 2020,9,1 ), LocalDate.of(2020,9,15), 299.00, excursion);
        List<Flights> flightsList = new ArrayList<> ();
        flightsList.add (flight1);
        flightsList.add (flight2);
        when (flightsRepository.findAll()).thenReturn (flightsList);
        //When
        List<Flights> list = flightsDbService.findAllFlights ();
        //Then
        Assert.assertEquals (2, list.size ());
        Assert.assertEquals (1L, list.get (0).getFlightId().longValue());
        Assert.assertEquals (2L, list.get (1).getFlightId().longValue());
    }
    @Test
    public void getFlightsTest(){
        //Given
        List<Hotel> hotelsItaly = new ArrayList<> ();
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        List<String> specialOffer = new ArrayList<> ();
        List<Observer> observers = new ArrayList<> ();
        User user = new User ( 1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly );
        Excursion excursion = new Excursion ( 1L, "Italy", 2000.00, LocalDate.of ( 2020, 8, 20 ), specialOffer, observers, hotelsItaly, null, weatherItaly, user);
        Flights flight1 = new Flights (1L,"Krakow", "Rome", LocalDate.of ( 2020,8,30 ), LocalDate.of(2020,9,15), 250.00, excursion);

        when(flightsRepository.findById(flight1.getFlightId ())).thenReturn( Optional.ofNullable(flight1));
        //When
        Optional<Flights> fetchedFlight = flightsDbService.getFlight (flight1.getFlightId());
        //Then
        Assert.assertNotNull(fetchedFlight);
    }

    @Test
    public void saveFlightsTest(){
        //Given
        List<Hotel> hotelsItaly = new ArrayList<> ();
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        List<String> specialOffer = new ArrayList<> ();
        List<Observer> observers = new ArrayList<> ();
        User user = new User ( 1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly );
        Excursion excursion = new Excursion ( 1L, "Italy", 2000.00, LocalDate.of ( 2020, 8, 20 ), specialOffer, observers, hotelsItaly, null, weatherItaly, user);
        Flights flight1 = new Flights (1L,"Krakow", "Rome", LocalDate.of ( 2020,8,30 ), LocalDate.of(2020,9,15), 250.00, excursion);

        when(flightsRepository.save(flight1)).thenReturn(flight1);
        //When
        Flights savedFlight= flightsDbService.saveFlight(flight1);
        //Then
        Assert.assertEquals(flight1, savedFlight);
        Assert.assertEquals(1L, savedFlight.getFlightId().longValue ());
        Assert.assertEquals("Rome", savedFlight.getArrivalAirport ());
        Assert.assertEquals (250.00, savedFlight.getPrice (), 2);
    }

    @Test
    public void deleteFlightsTest() {
        //Given
        List<Hotel> hotelsItaly = new ArrayList<> ();
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        List<String> specialOffer = new ArrayList<> ();
        List<Observer> observers = new ArrayList<> ();
        User user = new User ( 1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly );
        Excursion excursion = new Excursion ( 1L, "Italy", 2000.00, LocalDate.of ( 2020, 8, 20 ), specialOffer, observers, hotelsItaly, null, weatherItaly, user);
        Flights flight1 = new Flights (1L,"Krakow", "Rome", LocalDate.of ( 2020,8,30 ), LocalDate.of(2020,9,15), 250.00, excursion);

        when (flightsRepository.save (flight1)).thenReturn (flight1);
        //When
        flightsDbService.saveFlight (flight1);
        flightsDbService.deleteFlight (flight1.getFlightId());
        //Then
        Assert.assertFalse (flightsDbService.isExist(flight1.getFlightId()));
    }
}