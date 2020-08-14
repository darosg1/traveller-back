package com.crud.traveller.controller;

import com.crud.traveller.LocalDateAdapter;
import com.crud.traveller.domain.FlightsDto;
import com.crud.traveller.entity.Excursion;
import com.crud.traveller.entity.Flights;
import com.crud.traveller.mapper.FlightsMapper;
import com.crud.traveller.service.FlightsDbService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith (SpringRunner.class)
@WebMvcTest(FlightsController.class)
public class FlightsControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FlightsDbService flightsDbService;
    @MockBean
    private FlightsMapper flightsMapper;

    @Test
    public void getFlights() throws Exception {
        //Given
        Flights flights1 = new Flights (1L, "Warsaw", "Rome", LocalDate.of(2020,9,1),
                LocalDate.of(2020,9,3), 150.00, new Excursion ());
        List<Flights> flightsList = new ArrayList<> ();
        flightsList.add (flights1);

        FlightsDto flightsDto1 = new FlightsDto (1L, "Warsaw", "Rome", LocalDate.of(2020,9,1),
                LocalDate.of(2020,9,3), 150.00, new Excursion ());
        List<FlightsDto> flightsDtoList = new ArrayList<> ();
        flightsDtoList.add (flightsDto1);

        when (flightsMapper.mapToFlightsDtoList(flightsList)).thenReturn (flightsDtoList);
        when (flightsDbService.findAllFlights ()).thenReturn(flightsList);
        //When&Then
        mockMvc.perform (get("/v1/flights" ).contentType( MediaType.APPLICATION_JSON))
                .andExpect (status().isOk())
                .andExpect (jsonPath("$", hasSize (1)))
                .andExpect (jsonPath("$[0].flightId", is (1)))
                .andExpect(jsonPath("$[0].departureAirport", is("Warsaw")))
                .andExpect(jsonPath("$[0].arrivalAirport", is("Rome")));
    }

    @Test
    public void getFlight() throws Exception{
        //Given
        Flights flights1 = new Flights (1L, "Warsaw", "Rome", LocalDate.of(2020,9,1),
                LocalDate.of(2020,9,3), 150.00, new Excursion ());
        FlightsDto flightsDto1 = new FlightsDto (1L, "Warsaw", "Rome", LocalDate.of(2020,9,1),
                LocalDate.of(2020,9,3), 150.00, new Excursion ());
        when(flightsDbService.getFlight (flights1.getFlightId())).thenReturn( Optional.of(flights1));
        when(flightsMapper.mapToFlightsDto(flights1)).thenReturn(flightsDto1);
        //When&Then
        mockMvc.perform(get("/v1/flights/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightId", is (1)))
                .andExpect(jsonPath("$.departureAirport", is("Warsaw")))
                .andExpect(jsonPath("$.arrivalAirport", is("Rome")));
    }

    @Test
    public void createFlight() throws Exception {
        //Given
        Flights flights1 = new Flights (1L, "Warsaw", "Rome", LocalDate.of(2020,9,1),
                LocalDate.of(2020,9,3), 150.00, new Excursion ());
        FlightsDto flightsDto1 = new FlightsDto (1L, "Warsaw", "Rome", LocalDate.of(2020,9,1),
                LocalDate.of(2020,9,3), 150.00, new Excursion ());
        when (flightsMapper.mapToFlights (flightsDto1)).thenReturn (flights1);
        when (flightsDbService.saveFlight (ArgumentMatchers.any (Flights.class))).thenReturn(flights1);
        when (flightsMapper.mapToFlightsDto(flights1)).thenReturn (flightsDto1);
        Gson gson = new GsonBuilder ()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter ())
                .create();
        String jsonContent = gson.toJson (flightsDto1);
        //When&Then
        mockMvc.perform(post("/v1/flights").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateFlight() throws Exception {
        //Given
        Flights flights1 = new Flights (1L, "Warsaw", "Rome", LocalDate.of(2020,9,1),
                LocalDate.of(2020,9,3), 150.00, new Excursion ());
        FlightsDto flightsDto1 = new FlightsDto (1L, "Krakow", "Rome", LocalDate.of(2020,9,1),
                LocalDate.of(2020,9,4), 140.00, new Excursion ());
        when(flightsMapper.mapToFlights (flightsDto1)).thenReturn(flights1);
        when(flightsDbService.saveFlight (ArgumentMatchers.any(Flights.class))).thenReturn(flights1);
        when(flightsMapper.mapToFlightsDto(flights1)).thenReturn(flightsDto1);
        Gson gson = new GsonBuilder ()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter ())
                .create();
        String jsonContent = gson.toJson (flightsDto1);
        //When&Then
        mockMvc.perform(put("/v1/flights").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightId", is(1)))
                .andExpect(jsonPath("$.departureAirport", is("Krakow")))
                .andExpect(jsonPath("$.arrivalAirport", is("Rome")));
    }
}
