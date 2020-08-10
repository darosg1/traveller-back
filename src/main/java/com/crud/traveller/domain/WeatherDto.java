package com.crud.traveller.domain;

import com.crud.traveller.entity.Excursion;
import com.crud.traveller.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {
    @JsonProperty("query")
    private String query;
   // @JsonProperty("current")
   // private List<CurrentDto> current;
    private Excursion excursion;
    private User user;
}