package com.casestudy.datalayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
    private String status;
    @CreationTimestamp
    private LocalDateTime createdOn;

    public Transactions(Long quantity, String transactionType, float price, String status, Stock stock) {
        this.quantity = quantity;
        this.transactionType = transactionType;
        this.price = price;
        this.status = status;
        this.stock = stock;
    }
}
