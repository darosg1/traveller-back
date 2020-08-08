package com.crud.traveller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Component
public class Currency {
    private String table;
    @Id
    private String no;
    private String effectiveDate;
    @OneToMany(targetEntity = Rates.class,
            mappedBy = "currency",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Rates> rates;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}