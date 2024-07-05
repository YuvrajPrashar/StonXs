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
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "stockId")
    private Stock stock;
    @ManyToOne
    @JoinColumn(name = "portfolioId")
    private Portfolio portfolio;
    private String transactionType;
    private Long quantity;
    private float price;
    private boolean isDeleted=false;
}
