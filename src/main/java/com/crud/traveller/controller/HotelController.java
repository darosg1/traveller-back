package com.crud.traveller.controller;

import com.crud.traveller.domain.HotelDto;
import com.crud.traveller.exception.HotelNotFoundException;
import com.crud.traveller.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/v1")
public class HotelController {

    @Autowired
    HotelMapper hotelMapper;

   /* @RequestMapping(method = RequestMethod.GET, value = "/hotels")
    public List<HotelDto> getHotels() {
        return hotelMapper.mapToHotelDtoList (hotelDbService.findAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "hotels/{hotelId}")
    public HotelDto getHotel(@RequestParam Long userId) throws HotelNotFoundException {
        return hotelMapper.mapToHotelDto(hotelDbService.getHotel(hotelId).orElseThrow(HotelNotFoundException::new));
    }*/
}
