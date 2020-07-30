package com.crud.traveller.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CurrencyExchange {
    private String currency;
    private double amount;
    private double exchangeRate;
}

