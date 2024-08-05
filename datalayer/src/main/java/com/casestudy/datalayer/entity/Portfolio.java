package com.casestudy.datalayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
    @OneToMany(mappedBy = "portfolio")
    private List<Transactions> transactions;
    @OneToMany(mappedBy = "portfolio")
    private List<Holdings> holdings;
    private int investedValue;
    private int balance;
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    public Portfolio(int investedvalue, int balance) {
        this.investedValue = investedvalue;
        this.balance = balance;
    }
}
