package com.crud.traveller.currency.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CurrencyConfig {

    @Value("${currency.api.endpoint}")
    private String currencyApiEndpoint;
}