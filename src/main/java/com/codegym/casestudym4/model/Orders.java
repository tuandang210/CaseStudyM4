package com.codegym.casestudym4.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private String dates;

    public Orders() {
    }

    public Orders(Long id, User user, String date) {
        this.id = id;
        this.user = user;
        this.dates = date;
    }

    public Orders(User user, String date) {
        this.user = user;
        this.dates = date;
    }

    public Orders(User user) {
        this.user = user;
    }
}
