package com.crud.traveller.domain;

import com.crud.traveller.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long userId;
    private String userName;
    private String userKey;
    private List<Excursion> excursion;
    private List<Currency> currency;
    //private List<Weather> weatherForecast;
}