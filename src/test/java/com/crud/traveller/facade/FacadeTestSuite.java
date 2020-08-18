package com.crud.traveller.facade;

import com.crud.traveller.domain.ExcursionDto;
import com.crud.traveller.entity.*;
import com.crud.traveller.mapper.ExcursionMapper;
import com.crud.traveller.patterns.facade.ExcursionFacade;
import com.crud.traveller.patterns.validator.ExcursionValidator;
import com.crud.traveller.service.ExcursionDbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FacadeTestSuite {
    @InjectMocks
    private ExcursionFacade excursionFacade;

    @Mock
    private ExcursionDbService excursionDbService;

    @Mock
    private ExcursionValidator excursionValidator;

    @Mock
    private ExcursionMapper excursionMapper;

    @Test
    public void shouldFetchExcursions(){
        //Given
        ExcursionDto excursionDto1 = new ExcursionDto (1L, "Italy", 2000.00, LocalDate.of ( 2020, 8, 20), null, null, null, null, null, null);
        List<ExcursionDto> excursionsDto = new ArrayList<> ();
        excursionsDto.add (excursionDto1);

        Excursion excursion1 = new Excursion (1L, "Italy", 2000.00, LocalDate.of ( 2020, 8, 20), null, null, null, null, null, null);
        List<Excursion> excursions = new ArrayList<> ();
        excursions.add (excursion1);

        when(excursionDbService.findAllExcursions()).thenReturn(excursions);
        when(excursionMapper.mapToExcursionDtoList (anyList ())).thenReturn(excursionsDto);
        when(excursionValidator.validateExcursions (excursions)).thenReturn(excursions);

        //When
        List<ExcursionDto> dtos = excursionFacade.findExcursions ();
        //Then
        Assert.assertNotNull(dtos);
        Assert.assertEquals(1, dtos.size());
        excursionsDto.forEach(excursionDto -> {
            Assert.assertEquals("Italy", excursionDto.getDestination ());
            Assert.assertEquals(2000.00, excursionDto.getPrice (), 2);
        });
    }
}