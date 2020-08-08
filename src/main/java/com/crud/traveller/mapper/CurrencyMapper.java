package com.crud.traveller.mapper;

import com.crud.traveller.domain.CurrencyDto;
import com.crud.traveller.entity.Currency;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyMapper {
    public List<CurrencyDto> mapToCurrencyDtoList(final List<Currency> currencyList){
        return currencyList.stream ()
                .map ( e->new CurrencyDto (e.getTable (), e.getNo (), e.getEffectiveDate (),
                        e.getRates (), e.getUser ()))
                .collect ( Collectors.toList ());
    }
    public List<Currency> mapToCurrencyList(final List<CurrencyDto> currencyDtoList){
        return currencyDtoList.stream ()
                .map ( e->new Currency (e.getTable (), e.getNo (), e.getEffectiveDate (),
                        e.getRates (), e.getUser ()))
                .collect ( Collectors.toList ());
    }

}
