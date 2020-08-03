package com.crud.traveller.service;

import com.crud.traveller.entity.Hotel;
import com.crud.traveller.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HotelDbService {
    @Autowired
    HotelRepository hotelRepository;

    public List<Hotel> findAllHotels(){
        return hotelRepository.findAll ();
    }
    public Optional<Hotel> getHotel(final Long hotelId){
        return hotelRepository.findById (hotelId);
    }
    public Hotel saveHotel(final Hotel hotel){
        return hotelRepository.save (hotel);
    }
    public void deleteHotel(final Long hotelId){
        hotelRepository.deleteById (hotelId);
    }
    public boolean isExist(Long hotelId){
        return hotelRepository.existsById (hotelId);
    }
}
