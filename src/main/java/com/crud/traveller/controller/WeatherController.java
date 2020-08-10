package com.crud.traveller.controller;

import com.crud.traveller.domain.WeatherDto;
import com.crud.traveller.service.WeatherEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(name = "/v1")
public class WeatherController {
    @Autowired
    WeatherEmailService weatherEmailService;

    @RequestMapping(method = RequestMethod.GET, value = "/weather")
    public void getWeatherForecast() {
        List<WeatherDto> result = weatherEmailService.fetchWeather ();
        result.forEach ( weatherDto ->
                System.out.println ( weatherDto.getQuery ()));
    }
}
