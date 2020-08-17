package com.crud.traveller.controller;

import com.crud.traveller.domain.WeatherDto;
import com.crud.traveller.service.WeatherEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherEmailService weatherEmailService;

    @Test
    public void getWeather() throws Exception{
        //Given
        WeatherDto weatherDto = new WeatherDto ("Test");
        when(weatherEmailService.fetchWeather ()).thenReturn (weatherDto);

        //When & Then
        mockMvc.perform(get("/weather").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect (jsonPath ("$", hasSize (1)))
                .andExpect(jsonPath("$.query", is ("Test")));
    }
}