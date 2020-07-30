package com.crud.traveller.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WeatherDto {
    private double temperature;
    private double precipitation;
    private String cloudsType;
    private double windStrength;
    private String windDirection;
}
