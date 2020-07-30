package com.crud.traveller.repository;

import com.crud.traveller.entity.Flights;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface FlightsRepository extends CrudRepository <Flights, Long>{
    @Override
    List<Flights> findAll();

    @Override
    Optional<Flights> findById(Long flightId);

    @Override
    Flights save(Flights flights);

    @Override
    void deleteById(Long flightId);
}
