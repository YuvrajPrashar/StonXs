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
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID stockId;
    @Column(unique = true)
    private String stockName;
    private String sector;
    @Column(unique = true)
    private String stockSymbol;
    private String currentPrice;
    private boolean isDeleted=false;

    public Stock(String stockName, String sector, String stockSymbol, String currentPrice) {
        this.stockName = stockName;
        this.sector = sector;
        this.stockSymbol = stockSymbol;
        this.currentPrice = currentPrice;
    }
}
