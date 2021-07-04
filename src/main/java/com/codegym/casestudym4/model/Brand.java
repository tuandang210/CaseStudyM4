package com.codegym.casestudym4.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table (name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    public Brand() {
    }

    public Brand(String name) {
        this.name = name;
    }

    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
