package com.crud.traveller.domain;

import com.crud.traveller.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyExchangeDto {
    private Long exchangeId;
    private String currency;
    private User user;
}
