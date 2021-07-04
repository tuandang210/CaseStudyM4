package com.codegym.casestudym4.model;

import javax.persistence.*;

@Entity
@Table(name = "orderdetails")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Orders orders;
    @ManyToOne
    private Product product;
    private Long quantity;

    public OrderDetail() {
    }

    public OrderDetail(Long id, Orders orders, Product product, Long quantity) {
        this.id = id;
        this.orders = orders;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderDetail(Orders orders, Product product, Long quantity) {
        this.orders = orders;
        this.product = product;
        this.quantity = quantity;
    }
}
