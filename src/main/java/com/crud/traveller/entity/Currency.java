package com.crud.traveller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Component
public class Currency {
    private String table;
    private String no;
    private String effectiveDate;
    private List<Rates> rates;
    private User user;
}