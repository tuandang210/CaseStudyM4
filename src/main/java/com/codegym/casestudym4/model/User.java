package com.codegym.casestudym4.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String fullName;
    private Double totalSpent;

    public User() {
    }

    public User(Long id, String username, String password, String fullName, Double totalSpent) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.totalSpent = totalSpent;
    }

    public User(String username, String password, String fullName, Double totalSpent) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.totalSpent = totalSpent;
    }
}
