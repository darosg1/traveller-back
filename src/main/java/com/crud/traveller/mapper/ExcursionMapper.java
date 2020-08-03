package com.crud.traveller.mapper;

import com.crud.traveller.domain.ExcursionDto;
import com.crud.traveller.entity.Excursion;
import java.util.List;
import java.util.stream.Collectors;

public class ExcursionMapper {
    public Excursion mapToExcursion(final ExcursionDto excursionDto){
        return new Excursion (
            excursionDto.getExcursionId (),
            excursionDto.getDestination (),
            excursionDto.getPrice (),
            excursionDto.getDepartureDate (),
            excursionDto.getHotels (),
            excursionDto.getFlights (),
            excursionDto.getWeather (),
            excursionDto.getUser ());
    }
    public ExcursionDto mapToExcursionDto(final Excursion excursion){
        return new ExcursionDto (
                excursion.getExcursionId (),
                excursion.getDestination (),
                excursion.getPrice (),
                excursion.getDepartureDate (),
                excursion.getHotels (),
                excursion.getFlights (),
                excursion.getWeather (),
                excursion.getUser ());
    }
    public List<ExcursionDto> mapToExcursionDtoList(final List<Excursion> excursionList){
        return excursionList.stream ()
                .map ( e->new ExcursionDto (e.getExcursionId (), e.getDestination (),
                        e.getPrice (), e.getDepartureDate (), e.getHotels (), e.getFlights (),
                        e.getWeather (), e.getUser ()))
                .collect ( Collectors.toList ());
    }
}
