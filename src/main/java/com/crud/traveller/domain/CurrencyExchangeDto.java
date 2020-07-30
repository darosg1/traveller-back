package com.crud.traveller.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CurrencyExchangeDto {
    private String currency;
    private double amount;
    private double exchangeRate;
}
