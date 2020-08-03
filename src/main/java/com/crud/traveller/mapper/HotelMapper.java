package com.crud.traveller.mapper;

import com.crud.traveller.domain.HotelDto;
import com.crud.traveller.entity.Hotel;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {
    public Hotel mapToHotel(final HotelDto hotelDto) {
        return new Hotel (
                hotelDto.getHotelId (),
                hotelDto.getDestination (),
                hotelDto.getHotelName (),
                hotelDto.getRoomType (),
                hotelDto.getArrivalDate (),
                hotelDto.getDepartureDate (),
                hotelDto.getGuestsNumber (),
                hotelDto.getPrice (),
                hotelDto.getExcursion ());
    }
    public HotelDto mapToHotelDto(final Hotel hotel) {
        return new HotelDto (
                hotel.getHotelId (),
                hotel.getDestination (),
                hotel.getHotelName (),
                hotel.getRoomType (),
                hotel.getArrivalDate (),
                hotel.getDepartureDate (),
                hotel.getGuestsNumber (),
                hotel.getPrice (),
                hotel.getExcursion ());
    }
    public List<HotelDto> mapToHotelDtoList(final List<Hotel> hotelList){
        return hotelList.stream ()
                .map (h-> new HotelDto (h.getHotelId (), h.getDestination (), h.getHotelName (),
                        h.getRoomType (), h.getArrivalDate (), h.getDepartureDate (),
                        h.getGuestsNumber (), h.getPrice (), h.getExcursion ()))
                .collect( Collectors.toList ());
    }
}
