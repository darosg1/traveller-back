package com.crud.traveller.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherDto {
    private double temperature;
    private double precipitation;
    private String cloudsType;
    private double windStrength;
    private String windDirection;
}
