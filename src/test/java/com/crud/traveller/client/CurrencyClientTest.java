package com.crud.traveller.client;

import com.crud.traveller.currency.client.CurrencyClient;
import com.crud.traveller.currency.config.CurrencyConfig;
import com.crud.traveller.domain.CurrencyDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyClientTest {
    @InjectMocks
    private CurrencyClient currencyClient;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private CurrencyConfig currencyConfig;

    @Before
    public void init() {
        when(currencyConfig.getCurrencyApiEndpoint()).thenReturn("http://test.com");
    }

    @Test
    public void shouldFetchCurrency() throws URISyntaxException {
        //Given
        CurrencyDto[] currency = new CurrencyDto[1];
        currency[0] = new CurrencyDto ("Table A", "test", "test", null, null);
        URI uri = new URI("http://test.com");
        when(restTemplate.getForObject(uri, CurrencyDto[].class)).thenReturn(currency);
        //When
        List<CurrencyDto> fetchedCurrency = currencyClient.getExchangeTable ();
        //Then
        assertEquals(1, fetchedCurrency.size());
        assertEquals("Table A", fetchedCurrency.get(0).getTable ());
    }
}
