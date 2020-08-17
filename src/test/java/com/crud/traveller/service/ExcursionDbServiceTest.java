package com.crud.traveller.service;

import com.crud.traveller.entity.*;
import com.crud.traveller.patterns.observer.Observer;
import com.crud.traveller.repository.ExcursionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ExcursionDbServiceTest {
    @InjectMocks
    ExcursionDbService excursionDbService;
    @Mock
    ExcursionRepository excursionRepository;

    @Test
    public void findAllExcursionsTest(){
        //Given
        List<Hotel> hotelsItaly = new ArrayList<> ();
        List<Flights> flightsItaly = new ArrayList<> ();
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        List<String> specialOffer = new ArrayList<> ();
        List<Observer> observers = new ArrayList<> ();
        User user1 = new User(1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);
        Excursion excursion1 = new Excursion (1L, "Italy", 2000.00, LocalDate.of ( 2020, 8, 20), specialOffer, observers, hotelsItaly, flightsItaly, weatherItaly, user1);
        Excursion excursion2 = new Excursion (2L, "Italy", 5000.00, LocalDate.of ( 2020, 8, 23), Collections.emptyList (), observers, hotelsItaly, flightsItaly, weatherItaly, user1);

        List <Excursion> excursionList = new ArrayList<> ();
        excursionList.add ( excursion1 );
        excursionList.add ( excursion2 );
        when(excursionRepository.findAll ()).thenReturn (excursionList);
        //When
        List<Excursion> list=excursionDbService.findAllExcursions ();
        //Then
        Assert.assertEquals(2, list.size ());
        Assert.assertEquals(1L, list.get(0).getExcursionId ().longValue());
        Assert.assertEquals(2L, list.get(1).getExcursionId ().longValue());
    }

    @Test
    public void getExcursionTest(){
        //Given
        List<Hotel> hotelsItaly = new ArrayList<> ();
        List<Flights> flightsItaly = new ArrayList<> ();
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        List<String> specialOffer = new ArrayList<> ();
        List<Observer> observers = new ArrayList<> ();
        User user1 = new User(1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);
        Excursion excursion1 = new Excursion (1L, "Italy", 2000.00, LocalDate.of ( 2020, 8, 20), specialOffer, observers, hotelsItaly, flightsItaly, weatherItaly, user1);

        when(excursionRepository.findById(excursion1.getExcursionId ())).thenReturn( Optional.ofNullable(excursion1));
        //When
        Optional<Excursion> fetchedExcursion = excursionDbService.getExcursion ( excursion1.getExcursionId () );
        //Then
        Assert.assertNotNull(fetchedExcursion);
    }

    @Test
    public void saveExcursionTest(){
        //Given
        List<Hotel> hotelsItaly = new ArrayList<> ();
        List<Flights> flightsItaly = new ArrayList<> ();
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        List<String> specialOffer = new ArrayList<> ();
        List<Observer> observers = new ArrayList<> ();
        User user = new User(1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);
        Excursion excursion = new Excursion (1L, "Italy", 2000.00, LocalDate.of ( 2020, 8, 20), specialOffer, observers, hotelsItaly, flightsItaly, weatherItaly, user);

        when(excursionRepository.save(excursion)).thenReturn(excursion);
        //When
        Excursion savedExcursion= excursionDbService.saveExcursion (excursion);
        //Then
        Assert.assertEquals(excursion, savedExcursion);
        Assert.assertEquals(1L, savedExcursion.getExcursionId().longValue ());
        Assert.assertEquals("Italy", savedExcursion.getDestination ());
        Assert.assertEquals (2000.00, savedExcursion.getPrice (), 2);
    }

    @Test
    public void deleteExcursionTest() {
        //Given
        List<Hotel> hotelsItaly = new ArrayList<> ();
        List<Flights> flightsItaly = new ArrayList<> ();
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        List<String> specialOffer = new ArrayList<> ();
        List<Observer> observers = new ArrayList<> ();
        User user = new User ( 1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly );
        Excursion excursion = new Excursion ( 1L, "Italy", 2000.00, LocalDate.of ( 2020, 8, 20 ), specialOffer, observers, hotelsItaly, flightsItaly, weatherItaly, user );

        when ( excursionRepository.save ( excursion ) ).thenReturn ( excursion );
        //When
        excursionDbService.saveExcursion ( excursion );
        excursionDbService.deleteExcursion ( excursion.getExcursionId () );
        //Then
        Assert.assertFalse ( excursionDbService.isExist ( excursion.getExcursionId () ) );
    }
}