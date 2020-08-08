package com.crud.traveller.repository;

import com.crud.traveller.entity.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<Currency, String> {
    @Override
    List<Currency> findAll();

    @Override
    Optional<Currency> findById(String currencyExchangeId);
}
