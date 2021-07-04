package com.codegym.casestudym4.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String author;
    private String content;
    private Long rate;
    @ManyToOne
    private Product product;

    public Comment() {
    }

    public Comment(String author, String content, Long rate, Product product) {
        this.author = author;
        this.content = content;
        this.rate = rate;
        this.product = product;
    }

    public Comment(Long id, String author, String content, Long rate, Product product) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.rate = rate;
        this.product = product;
    }
}
