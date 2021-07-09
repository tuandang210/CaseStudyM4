package com.codegym.casestudym4.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private String content;
    private Long rate;
    @ManyToOne
    private Product product;
    private Long likes;

    private String date;

    public Comment() {
    }

    public Comment(User user, String content, Long rate, Product product, Long likes,String date) {
        this.user = user;
        this.content = content;
        this.rate = rate;
        this.product = product;
        this.likes = likes;
        this.date = date;
    }

    public Comment(User user, String content, Long rate, Product product,String date) {
        this.user = user;
        this.content = content;
        this.rate = rate;
        this.product = product;
        this.date = date;
    }

    public Comment(Long id, User user, String content, Long rate, Product product,String date) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.rate = rate;
        this.product = product;
        this.date = date;
    }
}
