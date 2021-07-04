package com.codegym.casestudym4.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String type;
    private Long seat;
    @ManyToOne
    private Color color;
    private Double buyPrice;
    private Double sellPrice;
    private Long quantity;
    @ManyToOne
    private Brand brand;
    @OneToMany
    private Set<Image> imageSet;

    public Product() {
    }

    public Product(Long id, String name, String type, Long seat, Color color, Double buyPrice, Double sellPrice, Long quantity, Brand brand, Set<Image> imageSet) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.seat = seat;
        this.color = color;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        this.brand = brand;
        this.imageSet = imageSet;
    }

    public Product(String name, String type, Long seat, Color color, Double buyPrice, Double sellPrice, Long quantity, Brand brand, Set<Image> imageSet) {
        this.name = name;
        this.type = type;
        this.seat = seat;
        this.color = color;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        this.brand = brand;
        this.imageSet = imageSet;
    }
}
