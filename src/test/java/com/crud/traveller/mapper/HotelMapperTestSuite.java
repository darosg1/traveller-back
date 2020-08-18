package com.crud.traveller.mapper;

import com.crud.traveller.domain.HotelDto;
import com.crud.traveller.entity.Hotel;
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
public class HotelMapperTestSuite {
    @Autowired
    HotelMapper hotelMapper;

    @Test
    public void mapToHotel(){
        //Given
        HotelDto hotelDtoItaly = new HotelDto (1L, "Rome", "Colosseum","double",
                LocalDate.of(2020,8,30), LocalDate.of(2020,9,15), 2, 100.00, null);
        //When
        Hotel mappingResult= hotelMapper.mapToHotel(hotelDtoItaly);
        //Then
        Assert.assertEquals(1L, mappingResult.getHotelId ().longValue ());
        Assert.assertEquals("Colosseum", mappingResult.getHotelName ());
        Assert.assertEquals (2, mappingResult.getGuestsNumber (), 2);
    }

    @Test
    public void mapToHotelDto(){
        //Given
        Hotel hotelItaly = new Hotel (1L, "Rome", "Colosseum","double",
                LocalDate.of(2020,8,30), LocalDate.of(2020,9,15), 2, 100.00, null);
        //When
        HotelDto mappingResult=hotelMapper.mapToHotelDto (hotelItaly);
        //Then
        Assert.assertEquals(1L, mappingResult.getHotelId ().longValue ());
        Assert.assertEquals("Rome", mappingResult.getDestination ());
        Assert.assertEquals (100.00, mappingResult.getPrice (), 2);
    }

    @Test
    public void mapToHotelDtoList(){
        //Given
        Hotel hotelItaly = new Hotel (1L, "Rome", "Colosseum","double",
                LocalDate.of(2020,8,30), LocalDate.of(2020,9,15), 2, 100.00, null);
        List<Hotel> hotelsList = new ArrayList<> ();
        hotelsList.add (hotelItaly);
        //When
        List<HotelDto> mappingResult=hotelMapper.mapToHotelDtoList(hotelsList);
        //Then
        Assert.assertEquals(1L, mappingResult.get(0).getHotelId ().longValue ());
        Assert.assertEquals("Rome", mappingResult.get(0).getDestination ());
        Assert.assertEquals (100.00, mappingResult.get(0).getPrice (), 2);
    }
}