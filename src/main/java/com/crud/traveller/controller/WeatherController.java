package com.crud.traveller.controller;

import com.crud.traveller.domain.WeatherDto;
import com.crud.traveller.service.WeatherEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class WeatherController {
    @Autowired
    WeatherEmailService weatherEmailService;

  /*  @RequestMapping(method = RequestMethod.GET, value = "/weather")
    public void getWeatherForecast() {
        System.out.println (weatherEmailService.fetchWeather ());
    }
*/
    @RequestMapping(method = RequestMethod.GET, value = "/weather")
    public void getWeatherForecast() {
        weatherEmailService.fetchWeather ();
    }
}
