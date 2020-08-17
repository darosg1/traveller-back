package com.crud.traveller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rates {
    private String currency;
    private String code;
    private Double mid;
}