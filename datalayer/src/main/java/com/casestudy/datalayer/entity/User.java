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
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private boolean isDeleted = false;
    @OneToOne(mappedBy = "user")
    private Portfolio portfolio;
    @OneToOne(mappedBy = "user")
    private Watchlist watchlist;


    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
