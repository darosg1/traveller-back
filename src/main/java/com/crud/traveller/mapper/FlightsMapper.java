package com.crud.traveller.mapper;

import com.crud.traveller.domain.FlightsDto;
import com.crud.traveller.entity.Flights;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightsMapper {
    public Flights mapToFlights (final FlightsDto flightsDto){
        return new Flights (
                flightsDto.getFlightId (),
                flightsDto.getDepartureAirport (),
                flightsDto.getArrivalAirport (),
                flightsDto.getDepartureDate (),
                flightsDto.getArrivalDate (),
                flightsDto.getPrice ());
    }
    public FlightsDto mapToFlightsDto(final Flights flights){
        return new FlightsDto (
                flights.getFlightId (),
                flights.getDepartureAirport (),
                flights.getArrivalAirport (),
                flights.getDepartureDate (),
                flights.getArrivalDate (),
                flights.getPrice ());
    }
    public List<FlightsDto> mapToFlightsDtoList (final List<Flights> flightsList){
        return flightsList.stream()
                .map (f-> new FlightsDto (f.getFlightId (), f.getDepartureAirport (),
                        f.getArrivalAirport (), f.getDepartureDate (), f.getArrivalDate (), f.getPrice ()))
                .collect (Collectors.toList ());
    }
}
