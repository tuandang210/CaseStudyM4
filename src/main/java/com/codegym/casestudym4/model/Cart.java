package com.codegym.casestudym4.model;

//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Data
//@Entity
//@Table(name = "carts")
//public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @OneToOne
//    private User user;
//    @OneToMany
//    private Set<Product> product;
//
//    public Cart() {
//    }
//
//    public Cart(Long id, User user, Set<Product> product) {
//        this.id = id;
//        this.user = user;
//        this.product = product;
//    }
//
//    public Cart(User user, Set<Product> product) {
//        this.user = user;
//        this.product = product;
//    }
//}

import java.util.HashMap;
import java.util.Map;
public class Cart {
    private Map<Product, Long> shoppingCart ;

    public Cart() {
        this.shoppingCart = new HashMap<>();
    }

    public Cart(Map<Product, Long> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Map<Product, Long> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Map<Product, Long> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public boolean isExistedInShoppingCart(Product product) {
        boolean isExisted = false;
        for (Map.Entry<Product, Long> item : shoppingCart.entrySet()) {
            if (item.getKey().getId().equals(product.getId())) {
                isExisted = true;
                break;
            }
        }
        return isExisted;
    }

    public void addProductToCart(Product product) {
        if (isExistedInShoppingCart(product)) {
            Long quantity = shoppingCart.get(product);
            shoppingCart.put(product, quantity + 1);
        } else {
            shoppingCart.put(product, 1L);
        }
    }
}