package com.crud.traveller.currency.client;

import com.crud.traveller.currency.config.CurrencyExchangeConfig;
import com.crud.traveller.domain.CurrencyExchangeDto;
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
public class CurrencyExchangeClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeClient.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CurrencyExchangeConfig currencyExchangeConfig;

    public List<CurrencyExchangeDto> getExchangeRate() {
        URI url = UriComponentsBuilder.fromHttpUrl ( currencyExchangeConfig.getCurrencyExchangeApiEndpoint () )
                .queryParam ( "latest" ).build ().encode ().toUri ();
        try {
            CurrencyExchangeDto[] currencyResponse = restTemplate.getForObject ( url, CurrencyExchangeDto[].class );
            return Arrays.asList ( ofNullable ( currencyResponse ).orElse ( new CurrencyExchangeDto[0] ) );
        } catch (RestClientException e) {
            LOGGER.error ( e.getMessage (), e );
            return new ArrayList<> ();
        }
    }

}
