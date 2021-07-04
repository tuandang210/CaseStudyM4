package com.codegym.casestudym4.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private Date date;

    public Orders() {
    }

    public Orders(Long id, User user, Date date) {
        this.id = id;
        this.user = user;
        this.date = date;
    }

    public Orders(User user, Date date) {
        this.user = user;
        this.date = date;
    }
}
