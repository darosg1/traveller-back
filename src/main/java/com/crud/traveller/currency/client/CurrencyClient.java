package com.crud.traveller.currency.client;

import com.crud.traveller.currency.config.CurrencyConfig;
import com.crud.traveller.domain.CurrencyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class CurrencyClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyClient.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CurrencyConfig currencyConfig;

    public List<CurrencyDto> getExchangeTable () {
        URI url = UriComponentsBuilder.fromHttpUrl ( currencyConfig.getCurrencyApiEndpoint () )
                .build ().encode ().toUri ();
        try {
            CurrencyDto[] currencyResponse = restTemplate.getForObject ( url, CurrencyDto[].class );
            return Arrays.asList ( ofNullable ( currencyResponse ).orElse ( new CurrencyDto[0] ) );
        } catch (RestClientException e) {
            LOGGER.error ( e.getMessage (), e );
            return new ArrayList<> ();
        }
    }
/*
   public List<CurrencyDto> getExchangeTable() {
        CurrencyDto[] currencyResponse = restTemplate.getForObject(
                currencyConfig.getCurrencyApiEndpoint (),
                CurrencyDto[].class);
        return Arrays.asList(currencyResponse);
    }*/
}
