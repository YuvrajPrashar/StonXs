package com.casestudy.datalayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID portfolioId;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToMany
    @JoinTable(
            name = "portfolio_stock",
            joinColumns = @JoinColumn(name = "portfolioId"),
            inverseJoinColumns = @JoinColumn(name = "stockId")
    )
    private List<Stock> stock;
    @OneToMany(mappedBy = "portfolio")
    private List<Transactions> transactions;
    private int profit;
    private int loss;
    private int investedValue;
    private int balance;
    private boolean isDeleted = false;

    @ElementCollection
    @CollectionTable(name = "portfolio_stock_quantity", joinColumns = @JoinColumn(name = "portfolioId"))
    @MapKeyColumn(name = "stock_id")
    @Column(name = "quantity")
    private Map<Stock, Integer> stocksAndQuantity;

    public Portfolio(int investedvalue, int profit, int loss, List<Stock> stocks, int balance) {
        this.investedValue = investedvalue;
        this.profit = profit;
        this.loss = loss;
        this.stock = stocks;
        this.balance = balance;
    }
    public void addOrUpdateStock(Stock stock, int quantity) {
        stocksAndQuantity.merge(stock, quantity, Integer::sum);
    }
}