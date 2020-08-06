package com.crud.traveller.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyExchange {
    @Id
    private Long exchangeId;
    private String currency;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}

