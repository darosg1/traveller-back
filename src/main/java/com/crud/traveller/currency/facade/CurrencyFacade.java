/*package com.crud.traveller.currency.facade;

import com.crud.traveller.currency.validator.CurrencyValidator;
import com.crud.traveller.domain.CurrencyDto;
import com.crud.traveller.entity.Currency;
import com.crud.traveller.mapper.CurrencyMapper;
import com.crud.traveller.service.CurrencyDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CurrencyFacade {
    @Autowired
    CurrencyDbService currencyDbService;

    @Autowired
    CurrencyMapper currencyMapper;

    @Autowired
    CurrencyValidator currencyValidator;

    public List<CurrencyDto>findCurrencyExchange(){
        List<Currency> currencyExchange = currencyDbService.findAllExchanges ();
        List<Currency> filteredCurrencyExchange = currencyValidator.validateCurrencyExchange(currencyExchange);
                return currencyMapper.mapToCurrencyDtoList ( filteredCurrencyExchange );
    }
}
*/