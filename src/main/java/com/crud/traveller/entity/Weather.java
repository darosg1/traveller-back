package com.crud.traveller.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Weather {
    private double temperature;
    private double precipitation;
    private String cloudsType;
    private double windStrength;
    private String windDirection;
}
