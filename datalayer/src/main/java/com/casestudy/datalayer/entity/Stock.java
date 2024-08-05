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

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    private BigInteger companyValuation;

    private BigInteger marketCap;

    private String category;

    @Transient
    private BigDecimal currentPrice;

    public Stock(UUID stockId, String stockName, String sector, String stockSymbol, BigDecimal currentPrice) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.sector = sector;
        this.stockSymbol = stockSymbol;
        this.currentPrice = currentPrice;
        updateDerivedFields();
    }

    public Stock(UUID stockId, String stockName, String stockSymbol, String sector, BigInteger companyValuation, BigInteger marketCap) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.sector = sector;
        this.stockSymbol = stockSymbol;
        this.companyValuation = companyValuation;
        this.marketCap = marketCap;
        updateDerivedFields();
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    public void updateDerivedFields() {
        calculateCurrentPrice();
        companyCategory();
    }

        private void calculateCurrentPrice() {
            if (marketCap != null && companyValuation != null && companyValuation.compareTo(BigInteger.ZERO) != 0) {
                this.currentPrice = new BigDecimal(marketCap).divide(new BigDecimal(companyValuation), 2, BigDecimal.ROUND_HALF_UP);
            } else {
                this.currentPrice = BigDecimal.ZERO;
            }
        }

    private void companyCategory() {
        if (this.companyValuation.compareTo(BigInteger.valueOf(10000000)) > 0) {
            this.category = "large-cap";
        } else if (this.companyValuation.compareTo(BigInteger.valueOf(1000000)) > 0) {
            this.category = "mid-cap";
        } else {
            this.category = "small-cap";
        }
    }
}
