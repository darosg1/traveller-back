package com.crud.traveller.controller;

import com.crud.traveller.domain.FlightsDto;
import com.crud.traveller.exception.FlightsNotFoundException;
import com.crud.traveller.mapper.FlightsMapper;
import com.crud.traveller.service.FlightsDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/v1")
public class FlightsController {
    @Autowired
    FlightsMapper flightsMapper;
    @Autowired
    FlightsDbService flightsDbService;

    @RequestMapping(method = RequestMethod.GET, value="/flights")
    public List<FlightsDto> getFlights(){
        return flightsMapper.mapToFlightsDtoList (flightsDbService.findAllFlights ());
    }

    @RequestMapping(method = RequestMethod.GET, value="/flights/{flightId}")
    public FlightsDto getFlight(@RequestParam Long flightId) throws FlightsNotFoundException {
        return flightsMapper.mapToFlightsDto (flightsDbService.getFlight(flightId).orElseThrow (FlightsNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/flights", consumes = APPLICATION_JSON_VALUE)
    public void createFlight(@RequestBody FlightsDto flightsDto){
        flightsDbService.saveFlight (flightsMapper.mapToFlights (flightsDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/flights", consumes = APPLICATION_JSON_VALUE)
    public FlightsDto updateFlight(@RequestBody FlightsDto flightsDto){
        return flightsMapper.mapToFlightsDto (flightsDbService.saveFlight (flightsMapper.mapToFlights (flightsDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value ="/flights/{flightId}")
    public void deleteFlight(@RequestParam Long flightId) throws FlightsNotFoundException{
        if (flightsDbService.isExist (flightId)){
            flightsDbService.deleteFlight (flightId);
        }else{
            throw new FlightsNotFoundException();
        }
    }
}
