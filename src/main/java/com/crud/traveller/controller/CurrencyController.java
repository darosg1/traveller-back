package com.crud.traveller.controller;

import com.crud.traveller.currency.client.CurrencyClient;
import com.crud.traveller.domain.CurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class CurrencyController {
    @Autowired
    private CurrencyClient currencyClient;

    @RequestMapping(method = RequestMethod.GET, value = "/currency")
    public void getExchangeRate() {
       /* currencyFacade.findCurrencyExchange ();
    }*/
        List<CurrencyDto> exchange = currencyClient.getExchangeTable ();
        exchange.forEach ( currencyDto ->
                System.out.println (currencyDto.getTable ()+
                        currencyDto.getEffectiveDate ()+ currencyDto.getNo ()+currencyDto.getRates ()));
    }
}