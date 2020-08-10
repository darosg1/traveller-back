package com.crud.traveller.controller;

import com.crud.traveller.domain.ExcursionDto;
import com.crud.traveller.exception.ExcursionNotFoundException;
import com.crud.traveller.mapper.ExcursionMapper;
import com.crud.traveller.service.ExcursionDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/v1")
public class ExcursionController {
    @Autowired
    ExcursionMapper excursionMapper;
    @Autowired
    ExcursionDbService excursionDbService;

    @RequestMapping(method = RequestMethod.GET, value = "/excursions")
    public List<ExcursionDto> getExcursions() {
        return excursionMapper.mapToExcursionDtoList (excursionDbService.findAllExcursions ());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/excursions/{excursionId}")
    public ExcursionDto getExcursion(@RequestParam Long excursionId) throws ExcursionNotFoundException {
        return excursionMapper.mapToExcursionDto (excursionDbService.getExcursion (excursionId).orElseThrow(ExcursionNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/excursions", consumes = APPLICATION_JSON_VALUE)
    public void createExcursion(@RequestBody ExcursionDto excursionDto){
        excursionDbService.saveExcursion (excursionMapper.mapToExcursion (excursionDto));
    }

    @RequestMapping (method = RequestMethod.PUT, value = "/excursions", consumes = APPLICATION_JSON_VALUE)
    public ExcursionDto updateExcursion(@RequestBody ExcursionDto excursionDto) {
        return excursionMapper.mapToExcursionDto(excursionDbService.saveExcursion(excursionMapper.mapToExcursion (excursionDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/excursions/{excursionId}")
    public void deleteExcursion(@RequestParam Long excursionId) throws ExcursionNotFoundException {
        if (excursionDbService.isExist(excursionId)) {
            excursionDbService.deleteExcursion (excursionId);
        } else {
            throw new ExcursionNotFoundException();
        }
    }
}
