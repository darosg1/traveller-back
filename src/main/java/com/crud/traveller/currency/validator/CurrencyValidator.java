/*package com.crud.traveller.currency.validator;

import com.crud.traveller.entity.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger ( CurrencyValidator.class );

    public List<Currency> validateCurrencyExchange(final List<Currency> currencyList) {
        LOGGER.info ( "Starting filtering lists..." );
        List<Currency> filteredCurrencyList = currencyList.stream ()
                .filter ( currency -> !currency.getTable ().isEmpty ())
                .collect ( Collectors.toList ());
        LOGGER.info ( "Currency list has been filtered. Current list size: " + filteredCurrencyList.size () );
        return filteredCurrencyList;
    }
}
*/