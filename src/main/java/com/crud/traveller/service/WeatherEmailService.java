package com.crud.traveller.service;

import com.crud.traveller.config.AdminConfig;
import com.crud.traveller.domain.WeatherDto;
import com.crud.traveller.entity.Mail;
import com.crud.traveller.weather.client.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherEmailService {
    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private AdminConfig adminConfig;

    public WeatherDto fetchWeather(){
        simpleEmailService.send (new Mail (adminConfig.getAdminMail (), "Information about activity" , "Weather has been checked by User."));
        return weatherClient.getWeatherReport ();
    }
}
