package com.crud.traveller.controller;

import com.crud.traveller.currency.client.CurrencyClient;
import com.crud.traveller.domain.CurrencyDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CurrencyClient currencyClient;

    @Test
    public void getCurrencyExchangeTest() throws Exception{
        //Given
        CurrencyDto currencyDto = new CurrencyDto ("Test", "test", "test", null, null);
        List<CurrencyDto> exchange = new ArrayList<>();
        exchange.add (currencyDto);
        when(currencyClient.getExchangeTable ()).thenReturn (exchange);
        //When & Then
        mockMvc.perform(get("/v1/currency").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].table", is ("Test")));
    }
}

