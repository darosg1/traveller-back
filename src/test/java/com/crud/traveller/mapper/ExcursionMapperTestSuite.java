package com.crud.traveller.mapper;

import com.crud.traveller.domain.ExcursionDto;
import com.crud.traveller.entity.Excursion;
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
public class ExcursionMapperTestSuite {
    @Autowired
    ExcursionMapper excursionMapper;

    @Test
    public void mapToExcursion(){
        //Given
        ExcursionDto excursionDto = new ExcursionDto (1L, "Italy", 2000.00, LocalDate.of ( 2020,8,30 ), null, null, null, null, null, null);
        //When
        Excursion mappingResult= excursionMapper.mapToExcursion (excursionDto);
        //Then
        Assert.assertEquals(1L, mappingResult.getExcursionId ().longValue ());
        Assert.assertEquals("Italy", mappingResult.getDestination ());
        Assert.assertEquals (2000.00, mappingResult.getPrice (), 2);
    }

    @Test
    public void mapToExcursionDto(){
        //Given
        Excursion excursion = new Excursion (1L, "Italy", 2000.00, LocalDate.of ( 2020,8,30 ), null, null, null, null, null, null);
        //When
        ExcursionDto mappingResult= excursionMapper.mapToExcursionDto (excursion);
        //Then
        Assert.assertEquals(1L, mappingResult.getExcursionId ().longValue ());
        Assert.assertEquals("Italy", mappingResult.getDestination ());
        Assert.assertEquals (2000.00, mappingResult.getPrice (), 2);
    }

    @Test
    public void mapToExcursionDtoList(){
        //Given
        Excursion excursion = new Excursion (1L, "Italy", 2000.00, LocalDate.of ( 2020,8,30 ), null, null, null, null, null, null);
        List<Excursion> excursionList = new ArrayList<> ();
        excursionList.add (excursion);
        //When
        List<ExcursionDto> mappingResult= excursionMapper.mapToExcursionDtoList (excursionList);
        //Then
        Assert.assertEquals(1L, mappingResult.get(0).getExcursionId ().longValue ());
        Assert.assertEquals("Italy", mappingResult.get(0).getDestination ());
        Assert.assertEquals ( LocalDate.of (2020,8,30), mappingResult.get (0).getDepartureDate ());
    }

}
