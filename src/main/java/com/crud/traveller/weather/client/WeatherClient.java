package com.crud.traveller.weather.client;

import com.crud.traveller.weather.config.WeatherConfig;
import com.crud.traveller.domain.WeatherDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import static java.util.Optional.ofNullable;

@Component
public class WeatherClient {
    private static final Logger LOGGER = LoggerFactory.getLogger ( WeatherClient.class );
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WeatherConfig weatherConfig;

    public WeatherDto getWeatherReport() {
        URI url = UriComponentsBuilder.fromHttpUrl ( weatherConfig.getWeatherApiEndpoint () )
                .queryParam ( "access_key" + weatherConfig.getWeatherApiKey () )
                .queryParam ("query", "London")
                .build ().encode ().toUri ();
        try {
            WeatherDto weatherResponse = restTemplate.getForObject ( url, WeatherDto.class );
            return ofNullable ( weatherResponse ).orElse ( new WeatherDto());
        } catch (RestClientException e) {
            LOGGER.error ( e.getMessage (), e );
            return new WeatherDto ();
        }
    }
}
        //return restTemplate.getForObject ( url, WeatherDto.class );
    /*public List<WeatherDto> getWeatherReport() {
        URI url = UriComponentsBuilder.fromHttpUrl ( weatherConfig.getWeatherApiEndpoint () )
                .queryParam ( "access_key" + weatherConfig.getWeatherApiKey () )
                .queryParam ( "query", "London" )
                .build ().encode ().toUri ();
        try {
            WeatherDto[] weatherResponse = restTemplate.getForObject ( url, WeatherDto[].class );
            return Arrays.asList ( ofNullable ( weatherResponse ).orElse ( new WeatherDto[0] ) );
        } catch (RestClientException e) {
            LOGGER.error ( e.getMessage (), e );
            return new ArrayList<> ();
        }
    }*/




