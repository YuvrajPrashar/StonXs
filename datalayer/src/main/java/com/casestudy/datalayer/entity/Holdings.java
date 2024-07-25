package com.casestudy.datalayer.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Holdings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID holdingId;
   @ManyToOne
   @JoinColumn(name = "portfolioId")
    private Portfolio portfolio;
    @ManyToOne
    @JoinColumn(name = "stockId")
    private Stock stocks;
    private int quantity;
}
