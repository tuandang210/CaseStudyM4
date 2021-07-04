package com.codegym.casestudym4.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "colors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    public Color() {
    }

    public Color(String name) {
        this.name = name;
    }

    public Color(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
