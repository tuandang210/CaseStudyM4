package com.codegym.casestudym4.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;

    public Image() {
    }

    public Image(String image) {
        this.image = image;
    }

    public Image(Long id, String image) {
        this.id = id;
        this.image = image;
    }
}
