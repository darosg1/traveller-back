package com.crud.traveller.service;

import com.crud.traveller.entity.Flights;
import com.crud.traveller.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FlightsDbService {
    @Autowired
    FlightsRepository flightsRepository;

    public List<Flights> findAllFlights(){
        return flightsRepository.findAll ();
    }

    public Optional<Flights> getFlight(final Long flightId){
        return flightsRepository.findById (flightId);
    }

    public Flights save(final Flights flights){
        return flightsRepository.save (flights);
    }

    public void deleteFlight(final Long flightId){
        flightsRepository.deleteById (flightId);
    }
    public boolean isExist(Long flightId){
        return flightsRepository.existsById (flightId);
    }
}
