package com.casestudy.datalayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
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

    private boolean isDeleted = false;

    private BigInteger companyValuation;

    private BigInteger marketCap;

    @Transient
    private BigDecimal currentPrice;

    public Stock(UUID stockId, String stockName, String sector, String stockSymbol, BigDecimal currentPrice) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.sector = sector;
        this.stockSymbol = stockSymbol;
        this.companyValuation = companyValuation;
        this.marketCap = marketCap;
        calculateCurrentPrice();
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    public void calculateCurrentPrice() {
        if (marketCap != null && marketCap.compareTo(BigInteger.ZERO) != 0) {
            this.currentPrice = new BigDecimal(companyValuation).divide(new BigDecimal(marketCap), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
        } else {
            this.currentPrice = BigDecimal.ZERO;
        }
    }
}

