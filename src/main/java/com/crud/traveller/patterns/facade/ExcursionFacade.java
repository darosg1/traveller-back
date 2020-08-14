package com.crud.traveller.patterns.facade;

import com.crud.traveller.patterns.validator.ExcursionValidator;
import com.crud.traveller.domain.ExcursionDto;
import com.crud.traveller.entity.Excursion;
import com.crud.traveller.mapper.ExcursionMapper;
import com.crud.traveller.service.ExcursionDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ExcursionFacade {
    @Autowired
    ExcursionDbService excursionDbService;

    @Autowired
    ExcursionMapper excursionMapper;

    @Autowired
    ExcursionValidator excursionValidator;

    public List<ExcursionDto> findExcursions(){
        List<Excursion> existingExcursions = excursionDbService.findAllExcursions ();
        List<Excursion> filteredExcursions = excursionValidator.validateExcursions(existingExcursions);
        return excursionMapper.mapToExcursionDtoList (filteredExcursions );
    }
}
