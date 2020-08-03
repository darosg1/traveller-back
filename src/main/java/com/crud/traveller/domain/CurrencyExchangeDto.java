package com.crud.traveller.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyExchangeDto {
    private String currency;
    private double amount;
    private double exchangeRate;
}
