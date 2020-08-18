package com.crud.traveller.controller;

import com.crud.traveller.service.WeatherEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class WeatherController {
    @Autowired
    WeatherEmailService weatherEmailService;

    @RequestMapping(method = RequestMethod.GET, value = "/weather")
    public void getWeatherForecast() {
        weatherEmailService.fetchWeather ();
    }
}
