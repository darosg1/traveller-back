package com.crud.traveller.controller;

import com.crud.traveller.domain.HotelDto;
import com.crud.traveller.domain.UserDto;
import com.crud.traveller.exception.HotelNotFoundException;
import com.crud.traveller.mapper.HotelMapper;
import com.crud.traveller.service.HotelDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/v1")
public class HotelController {
    @Autowired
    HotelMapper hotelMapper;
    @Autowired
    HotelDbService hotelDbService;

    @RequestMapping(method = RequestMethod.GET, value = "/hotels")
    public List<HotelDto> getHotels() {
        return hotelMapper.mapToHotelDtoList (hotelDbService.findAllHotels ());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hotels/{hotelId}")
    public HotelDto getHotel(@PathVariable Long hotelId) throws HotelNotFoundException {
        return hotelMapper.mapToHotelDto(hotelDbService.getHotel(hotelId).orElseThrow(HotelNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/hotels", consumes = APPLICATION_JSON_VALUE)
    public void createHotel(@RequestBody HotelDto hotelDto){
        hotelDbService.saveHotel(hotelMapper.mapToHotel(hotelDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/hotels", consumes = APPLICATION_JSON_VALUE)
    public HotelDto updateHotel(@RequestBody HotelDto hotelDto){
        return hotelMapper.mapToHotelDto (hotelDbService.saveHotel (hotelMapper.mapToHotel (hotelDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotels/{hotelId}")
    public void deleteHotel(@PathVariable Long hotelId) throws HotelNotFoundException{
        if (hotelDbService.isExist (hotelId)){
            hotelDbService.deleteHotel (hotelId);
        }else{
            throw new HotelNotFoundException();
        }
    }
}
