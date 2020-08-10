package com.crud.traveller.weather.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class WeatherConfig {

    @Value("${weather.api.endpoint}")
    private String weatherApiEndpoint;
    @Value ("${weather.api.access_key}")
    private String weatherApiKey;
}
