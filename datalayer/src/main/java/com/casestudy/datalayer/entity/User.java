package com.casestudy.datalayer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Full name is mandatory")
    private String fullname;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    @OneToOne(mappedBy = "user")
    private Portfolio portfolio;

    @OneToOne(mappedBy = "user")
    private Watchlist watchlist;

    @Column(columnDefinition = "boolean default false")
    private boolean isAdmin;

    public User(String username, String password, String email , String fullname) {
        this.fullname=fullname;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
