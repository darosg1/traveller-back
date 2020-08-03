package com.crud.traveller.repository;

import com.crud.traveller.entity.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface HotelRepository extends CrudRepository<Hotel, Long> {
    @Override
    List<Hotel> findAll();

    @Override
    Optional<Hotel> findById(Long hotelId);

    @Override
    Hotel save(Hotel hotel);

    @Override
    void deleteById(Long hotelId);

    @Override
    long count();
}

