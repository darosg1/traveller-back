package com.crud.traveller.repository;

import com.crud.traveller.entity.Flights;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FlightsRepository extends CrudRepository<Flights, Long> {
    @Override
    List<Flights> findAll();

    @Override
    Optional<Flights> findById(Long flightId);

    @Override
    Flights save(Flights flights);

    @Override
    void deleteById(Long flightId);
}
