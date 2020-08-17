package com.crud.traveller.client;

import com.crud.traveller.domain.WeatherDto;
import com.crud.traveller.weather.client.WeatherClient;
import com.crud.traveller.weather.config.WeatherConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherClientTest {
    @InjectMocks
    private WeatherClient weatherClient;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private WeatherConfig weatherConfig;

    @Before
    public void init() {
        when(weatherConfig.getWeatherApiEndpoint()).thenReturn("http://test.com");
        when(weatherConfig.getWeatherApiKey()).thenReturn("test");
    }

    @Test
    public void shouldFetchWeather() throws URISyntaxException {
        //Given
        WeatherDto weather = new WeatherDto ("Rome");
        URI uri = new URI("http://test.com?access_key=test&query=Rome");
        when(restTemplate.getForObject(uri, WeatherDto.class)).thenReturn(weather);
        //When
        WeatherDto fetchedWeather = weatherClient.getWeatherReport ();
        //Then
        assertEquals("Rome", fetchedWeather.getQuery ());
    }
}