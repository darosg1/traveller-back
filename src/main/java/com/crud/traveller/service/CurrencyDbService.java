package com.crud.traveller.service;

import com.crud.traveller.entity.Currency;
import com.crud.traveller.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyDbService {
    @Autowired
    CurrencyRepository currencyRepository;

    public List<Currency> findAllExchanges(){
        return currencyRepository.findAll ();
    }
    public Optional<Currency> getCurrencyExchange(final String currencyId) {
        return currencyRepository.findById (currencyId);
    }
}