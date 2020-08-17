package com.crud.traveller.service;

import com.crud.traveller.entity.*;
import com.crud.traveller.repository.HotelRepository;
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
public class HotelDbServiceTest {
    @InjectMocks
    HotelDbService hotelDbService;
    @Mock
    HotelRepository hotelRepository;

    @Test
    public void findAllHotelsTest() {
        //Given
        Excursion excursion = new Excursion();
        Hotel hotelItaly1 = new Hotel (1L, "Rome", "Colosseum","double",
                LocalDate.of(2020,8,30), LocalDate.of(2020,9,15), 2, 100.00, excursion);
        Hotel hotelItaly2 = new Hotel (2L, "Rome", "Colosseum2","single",
                LocalDate.of(2020,8,30), LocalDate.of(2020,9,15), 1, 50.00, excursion);
        List<Hotel> hotelsItaly = new ArrayList<>();
        hotelsItaly.add (hotelItaly1);
        hotelsItaly.add (hotelItaly2);

        when (hotelRepository.findAll()).thenReturn (hotelsItaly);
        //When
        List<Hotel> list = hotelDbService.findAllHotels ();
        //Then
        Assert.assertEquals (2, list.size ());
        Assert.assertEquals (1L, list.get (0).getHotelId().longValue());
        Assert.assertEquals (2L, list.get (1).getHotelId().longValue());
    }

    @Test
    public void getHotelTest(){
        //Given
        Excursion excursion = new Excursion();
        Hotel hotelItaly1 = new Hotel (1L, "Rome", "Colosseum","double",
                LocalDate.of(2020,8,30), LocalDate.of(2020,9,15), 2, 100.00, excursion);

        when(hotelRepository.findById(hotelItaly1.getHotelId ())).thenReturn( Optional.ofNullable(hotelItaly1));
        //When
        Optional<Hotel> fetchedHotel = hotelDbService.getHotel (hotelItaly1.getHotelId());
        //Then
        Assert.assertNotNull(fetchedHotel);
    }

    @Test
    public void saveHotelTest(){
        //Given
        Excursion excursion = new Excursion();
        Hotel hotelItaly1 = new Hotel (1L, "Rome", "Colosseum","double",
                LocalDate.of(2020,8,30), LocalDate.of(2020,9,15), 2, 100.00, excursion);

        when(hotelRepository.save(hotelItaly1)).thenReturn(hotelItaly1);
        //When
        Hotel savedHotel= hotelDbService.saveHotel (hotelItaly1);
        //Then
        Assert.assertEquals(hotelItaly1, savedHotel);
        Assert.assertEquals(1L, savedHotel.getHotelId().longValue());
        Assert.assertEquals("Rome", savedHotel.getDestination());
        Assert.assertEquals (100.00, savedHotel.getPrice(), 2);
    }

    @Test
    public void deleteFlightsTest() {
        //Given
        Excursion excursion = new Excursion();
        Hotel hotelItaly1 = new Hotel (1L, "Rome", "Colosseum","double",
                LocalDate.of(2020,8,30), LocalDate.of(2020,9,15), 2, 100.00, excursion);

        when (hotelRepository.save (hotelItaly1)).thenReturn (hotelItaly1);
        //When
        hotelDbService.saveHotel (hotelItaly1);
        hotelDbService.deleteHotel(hotelItaly1.getHotelId());
        //Then
        Assert.assertFalse (hotelDbService.isExist(hotelItaly1.getHotelId()));
    }
}