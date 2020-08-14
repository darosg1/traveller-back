package com.crud.traveller.mapper;

import com.crud.traveller.domain.ExcursionDto;
import com.crud.traveller.entity.*;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExcursionMapper {
    public Excursion mapToExcursion(final ExcursionDto excursionDto){
        return new Excursion (
            excursionDto.getExcursionId (),
            excursionDto.getDestination (),
            excursionDto.getPrice (),
            excursionDto.getDepartureDate (),
            excursionDto.getSpecialOffer (),
            excursionDto.getObservers (),
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
                excursion.getSpecialOffer (),
                excursion.getObservers (),
                excursion.getHotels (),
                excursion.getFlights (),
                excursion.getWeather (),
                excursion.getUser ());
    }
    public List<ExcursionDto> mapToExcursionDtoList(final List<Excursion> excursionList){
        return excursionList.stream ()
                .map ( e->new ExcursionDto (e.getExcursionId (), e.getDestination (),
                        e.getPrice (), e.getDepartureDate (),e.getSpecialOffer (), e.getObservers (),
                        e.getHotels (), e.getFlights (), e. getWeather(), e.getUser ()))
                .collect ( Collectors.toList ());
    }
}
