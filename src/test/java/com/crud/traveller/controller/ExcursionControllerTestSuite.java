package com.crud.traveller.controller;

import com.crud.traveller.LocalDateAdapter;
import com.crud.traveller.domain.ExcursionDto;
import com.crud.traveller.entity.*;
import com.crud.traveller.mapper.ExcursionMapper;
import com.crud.traveller.patterns.facade.ExcursionFacade;
import com.crud.traveller.service.ExcursionDbService;
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
@WebMvcTest(ExcursionController.class)
public class ExcursionControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ExcursionMapper excursionMapper;

    @MockBean
    ExcursionDbService excursionDbService;

    @MockBean
    ExcursionFacade excursionFacade;

    @Test
    public void getExcursions() throws Exception {
        List<Hotel> hotelRome = new ArrayList<> ();
        List<Flights> flightsRome = new ArrayList<> ();
        User user1 = new User();

        ExcursionDto excursionDto = new ExcursionDto  (1L, "Rome", 250.00, LocalDate.of ( 2020,8, 29 ), null, null,
                hotelRome, flightsRome, null, user1);
        List<ExcursionDto> excursionsDtoList = new ArrayList<> ();
        excursionsDtoList.add (excursionDto);

        when (excursionFacade.findExcursions ()).thenReturn(excursionsDtoList);
        //When&Then
        mockMvc.perform (get("/v1/excursions" ).contentType( MediaType.APPLICATION_JSON))
                .andExpect (status().isOk())
                .andExpect (jsonPath("$", hasSize (1)))
                .andExpect (jsonPath("$[0].excursionId", is (1)))
                .andExpect(jsonPath("$[0].destination", is("Rome")))
                .andExpect(jsonPath("$[0].price", is(250.00)));
    }

    @Test
    public void getExcursion() throws Exception{
        //Given
        List<Hotel> hotelRome = new ArrayList<> ();
        List<Flights> flightsRome = new ArrayList<> ();
        User user1 = new User();
        Excursion excursion = new Excursion (1L, "Rome", 250.00, LocalDate.of ( 2020,8, 29 ), null, null,
                hotelRome, flightsRome, null, user1);
        ExcursionDto excursionDto = new ExcursionDto  (1L, "Rome", 250.00, LocalDate.of ( 2020,8, 29 ), null, null,
                hotelRome, flightsRome, null, user1);
        when(excursionDbService.getExcursion (excursion.getExcursionId ())).thenReturn( Optional.of(excursion));
        when(excursionMapper.mapToExcursionDto (excursion)).thenReturn(excursionDto);
        //When&Then
        mockMvc.perform(get("/v1/excursions/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.excursionId", is (1)))
                .andExpect(jsonPath("$.destination", is("Rome")))
                .andExpect(jsonPath("$.price", is(250.00)));
    }

    @Test
    public void createExcursion() throws Exception{
        //Given
        List<Hotel> hotelRome = new ArrayList<> ();
        List<Flights> flightsRome = new ArrayList<> ();
        User user1 = new User();
        Excursion excursion = new Excursion (1L, "Rome", 250.00, LocalDate.of ( 2020,8, 29 ), null, null,
                hotelRome, flightsRome, null, user1);
        ExcursionDto excursionDto = new ExcursionDto  (1L, "Rome", 250.00, LocalDate.of ( 2020,8, 29 ), null, null,
                hotelRome, flightsRome, null, user1);
        when (excursionMapper.mapToExcursion (excursionDto)).thenReturn (excursion);
        when (excursionDbService.saveExcursion (ArgumentMatchers.any (Excursion.class))).thenReturn(excursion);
        when (excursionMapper.mapToExcursionDto(excursion)).thenReturn (excursionDto);
        Gson gson = new GsonBuilder ()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter ())
                .create();
        String jsonContent = gson.toJson (excursionDto);
        //When&Then
        mockMvc.perform(post("/v1/excursions").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateExcursion() throws Exception {
        //Given
        List<Hotel> hotelRome = new ArrayList<> ();
        List<Flights> flightsRome = new ArrayList<> ();
        User user1 = new User();
        Excursion excursion = new Excursion (1L, "Rome", 250.00, LocalDate.of ( 2020,8, 29 ), null, null,
                hotelRome, flightsRome, null, user1);
        ExcursionDto excursionDto = new ExcursionDto  (1L, "Milan", 275.00, LocalDate.of ( 2021,8, 29 ), null, null,
                hotelRome, flightsRome, null, user1);
        when(excursionMapper.mapToExcursion (excursionDto)).thenReturn(excursion);
        when(excursionDbService.saveExcursion (ArgumentMatchers.any(Excursion.class))).thenReturn(excursion);
        when(excursionMapper.mapToExcursionDto(excursion)).thenReturn(excursionDto);
        Gson gson = new GsonBuilder ()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter ())
                .create();
        String jsonContent = gson.toJson (excursionDto);
        //When&Then
        mockMvc.perform(put("/v1/excursions").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.excursionId", is(1)))
                .andExpect(jsonPath("$.destination", is("Milan")))
                .andExpect(jsonPath("$.price", is(275.00)))
                .andExpect(jsonPath("$.departureDate", is("2021-08-29")));
    }
}
