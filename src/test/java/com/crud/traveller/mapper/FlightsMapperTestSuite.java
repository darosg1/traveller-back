package com.crud.traveller.mapper;

import com.crud.traveller.domain.FlightsDto;
import com.crud.traveller.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightsMapperTestSuite {
    @Autowired
    FlightsMapper flightsMapper;

    @Test
    public void mapToFlights(){
        //Given
        FlightsDto flightsDto = new FlightsDto (1L,"Krakow", "Rome", LocalDate.of ( 2020,8,30 ), LocalDate.of(2020,9,15), 250.00, null);
        //When
        Flights mappingResult= flightsMapper.mapToFlights(flightsDto);
        //Then
        Assert.assertEquals(1L, mappingResult.getFlightId ().longValue ());
        Assert.assertEquals("Krakow", mappingResult.getDepartureAirport ());
        Assert.assertEquals (250.00, mappingResult.getPrice (), 2);
    }

    @Test
    public void mapToFlightsDto(){
        //Given
        Flights flights = new Flights (1L,"Krakow", "Rome", LocalDate.of ( 2020,8,30 ), LocalDate.of(2020,9,15), 250.00, null);
        //When
        FlightsDto mappingResult=flightsMapper.mapToFlightsDto (flights);
        //Then
        Assert.assertEquals(1L, mappingResult.getFlightId ().longValue ());
        Assert.assertEquals("Krakow", mappingResult.getDepartureAirport ());
        Assert.assertEquals (250.00, mappingResult.getPrice (), 2);
    }

    @Test
    public void mapToFlightsDtoList(){
        //Given
        Flights flights = new Flights (1L,"Krakow", "Rome", LocalDate.of ( 2020,8,30 ), LocalDate.of(2020,9,15), 250.00, null);
        List<Flights> flightsList = new ArrayList<> ();
        flightsList.add (flights);
        //When
        List<FlightsDto> mappingResult= flightsMapper.mapToFlightsDtoList(flightsList);
        //Then
        Assert.assertEquals(1L, mappingResult.get(0).getFlightId ().longValue ());
        Assert.assertEquals("Krakow", mappingResult.get(0).getDepartureAirport ());
        Assert.assertEquals (250.00, mappingResult.get(0).getPrice (), 2);
    }
}
