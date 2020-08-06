package com.crud.traveller.controller;

import com.crud.traveller.currency.client.CurrencyExchangeClient;
import com.crud.traveller.domain.CurrencyExchangeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeClient currencyExchangeClient;

    @RequestMapping(method = RequestMethod.GET, value = "/currency")
    public void getExchangeRate() {
        List<CurrencyExchangeDto> exchange = currencyExchangeClient.getExchangeRate ();
        exchange.forEach(currencyExchangeDto ->
                System.out.println(currencyExchangeDto.getExchangeId ()));
    }
}