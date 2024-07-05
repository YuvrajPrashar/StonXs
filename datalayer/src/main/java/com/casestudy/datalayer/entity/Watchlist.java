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
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID watchlistId;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToMany
    @JoinTable(
            name = "watchlist_stock",
            joinColumns = @JoinColumn(name = "watchlistId"),
            inverseJoinColumns = @JoinColumn(name = "stockId")
    )
    private List<Stock> stock;
    private boolean isDeleted=false;


    public Watchlist(List<Stock> stocks) {
        this.stock = stocks;
    }
}
