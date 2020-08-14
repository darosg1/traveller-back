package com.crud.traveller.controller;

import com.crud.traveller.LocalDateAdapter;
import com.crud.traveller.domain.HotelDto;
import com.crud.traveller.entity.*;
import com.crud.traveller.mapper.HotelMapper;
import com.crud.traveller.service.HotelDbService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HotelController.class)
public class HotelControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HotelDbService hotelDbService;
    @MockBean
    private HotelMapper hotelMapper;

    @Test
    public void getHotels() throws Exception {
        //Given
        Hotel hotel = new Hotel (1L, "Rome", "Colosseum", "double", LocalDate.of(2020,9,1),
                LocalDate.of(2020,9,3), 2, 350.00, new Excursion ());
        List<Hotel> hotelList = new ArrayList<> ();
        hotelList.add (hotel);

        HotelDto hotelDto = new HotelDto (1L, "Rome", "Colosseum", "double", LocalDate.of(2020,9,1),
                LocalDate.of(2020,9,3), 2, 350.00, new Excursion ());
        List<HotelDto> hotelDtoList = new ArrayList<> ();
        hotelDtoList.add (hotelDto);

        when (hotelMapper.mapToHotelDtoList(hotelList)).thenReturn (hotelDtoList);
        when (hotelDbService.findAllHotels()).thenReturn(hotelList);
        //When&Then
        mockMvc.perform (get("/v1/hotels" ).contentType(MediaType.APPLICATION_JSON))
                .andExpect (status().isOk())
                .andExpect (jsonPath("$", hasSize (1)))
                .andExpect (jsonPath("$[0].hotelId", is (1)))
                .andExpect(jsonPath("$[0].destination", is("Rome")))
                .andExpect(jsonPath("$[0].hotelName", is("Colosseum")));
    }

    @Test
    public void getHotel() throws Exception{
        //Given
        Hotel hotel = new Hotel (1L, "Rome", "Colosseum", "double", LocalDate.of(2020,9,1),
                LocalDate.of(2020,9,3), 2, 350.00, new Excursion ());
        HotelDto hotelDto = new HotelDto (1L, "Rome", "Colosseum", "double", LocalDate.of(2020,9,1),
                LocalDate.of(2020,9,3), 2, 350.00, new Excursion ());
        when(hotelDbService.getHotel(hotel.getHotelId())).thenReturn( Optional.of(hotel));
        when(hotelMapper.mapToHotelDto(hotel)).thenReturn(hotelDto);
        //When&Then
        mockMvc.perform(get("/v1/hotels/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hotelId", is (1)))
                .andExpect(jsonPath("$.hotelName", is("Colosseum")))
                .andExpect(jsonPath("$.roomType", is("double")));
    }

    @Test
    public void createHotel() throws Exception {
        //Given
        Hotel hotel = new Hotel ( 1L, "Rome", "Colosseum", "double", LocalDate.of ( 2020, 9, 1 ),
                LocalDate.of ( 2020, 9, 3 ), 2, 350.00, new Excursion ());
        HotelDto hotelDto = new HotelDto ( 1L, "Rome", "Colosseum", "double", LocalDate.of ( 2020, 9, 1 ),
                LocalDate.of ( 2020, 9, 3 ), 2, 350.00, new Excursion ());
        when (hotelMapper.mapToHotel (hotelDto)).thenReturn (hotel);
        when (hotelDbService.saveHotel (ArgumentMatchers.any (Hotel.class))).thenReturn(hotel);
        when (hotelMapper.mapToHotelDto(hotel)).thenReturn (hotelDto);
        Gson gson = new GsonBuilder ()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter ())
                .create();
        String jsonContent = gson.toJson (hotelDto);
        //When&Then
        mockMvc.perform(post("/v1/hotels").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateHotel() throws Exception {
        //Given
        Hotel hotel = new Hotel ( 1L, "Rome", "Colosseum", "double", LocalDate.of ( 2020, 9, 1 ),
                LocalDate.of ( 2020, 9, 3 ), 2, 350.00, new Excursion ());
        HotelDto hotelDto = new HotelDto ( 1L, "Rome", "Colosseum", "single", LocalDate.of ( 2020, 9, 1 ),
                LocalDate.of ( 2020, 9, 3 ), 2, 350.00, new Excursion ());
        when(hotelMapper.mapToHotel(hotelDto)).thenReturn(hotel);
        when(hotelDbService.saveHotel (ArgumentMatchers.any(Hotel.class))).thenReturn(hotel);
        when(hotelMapper.mapToHotelDto(hotel)).thenReturn(hotelDto);
        Gson gson = new GsonBuilder ()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter ())
                .create();
        String jsonContent = gson.toJson (hotelDto);
        //When&Then
        mockMvc.perform(put("/v1/hotels").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hotelId", is(1)))
                .andExpect(jsonPath("$.hotelName", is("Colosseum")))
                .andExpect(jsonPath("$.roomType", is("single")));
    }
}