package com.casestudy.datalayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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
    private int totalvalue;
    private int balance;
    private boolean isDeleted = false;

    public Portfolio(int balance, int totalvalue, int profit, int loss, List<Stock> stocks) {
        this.balance = balance;
        this.totalvalue = totalvalue;
        this.profit = profit;
        this.loss = loss;
        this.stock = stocks;
    }
}