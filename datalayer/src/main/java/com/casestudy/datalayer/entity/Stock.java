package com.casestudy.datalayer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;
    private String stockName;
    private String sector;
    private String stockSymbol;
    private String currentPrice;
    private boolean isDeleted=false;

    public Stock(String stockName, String sector, String stockSymbol, String currentPrice) {
    }
}
