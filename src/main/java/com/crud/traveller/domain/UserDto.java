package com.crud.traveller.domain;

import com.crud.traveller.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long userId;
    private String userName;
    private String userKey;
    private Excursion excursion;
    private VacationPackage vacationPackage;
    private CurrencyExchange currencyExchange;
    private Weather weatherForecast;
}