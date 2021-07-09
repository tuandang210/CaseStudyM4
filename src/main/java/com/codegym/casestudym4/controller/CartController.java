package com.codegym.casestudym4.controller;


import com.codegym.casestudym4.model.OrderDetail;
import com.codegym.casestudym4.model.Orders;
import com.codegym.casestudym4.model.Product;
import com.codegym.casestudym4.model.User;
import com.codegym.casestudym4.model.dtoCart.OrderDto;
import com.codegym.casestudym4.model.dtoCart.ProductDto;
import com.codegym.casestudym4.service.order.IOrdersService;
import com.codegym.casestudym4.service.orderdetail.IOrderDetailService;
import com.codegym.casestudym4.service.product.IProductService;
import com.codegym.casestudym4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class CartController {
    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;


    @GetMapping("/checkQuantity/{product_id}/{buy_quantity}")
    public ResponseEntity<Boolean> checkQuantityLeft(@PathVariable("product_id") Long id
            , @PathVariable("buy_quantity") Long buy_quantity ){
        Optional<Product> productOptional = productService.findById(id);

        if (!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Boolean isLeft = (productOptional.get().getQuantity() - buy_quantity) >= 0;
        return new ResponseEntity<>(isLeft, HttpStatus.OK);
    }


    @PostMapping("/createOrder")
    public ResponseEntity<Orders> createNewOrder(@RequestBody OrderDto orderDto){
        Long user_id = orderDto.getUser_id();
        List<ProductDto> products = orderDto.getProducts();

        Optional<User> customer = userService.findById(user_id);
        if (customer.isPresent() && !products.isEmpty()){
            Orders realOrder = new Orders(customer.get());
            ordersService.save(realOrder);

            Optional<Long> order_id = ordersService.findLastOrderIdByUserId(user_id);
            if (order_id.isPresent()){
                Orders currentOrder = ordersService.findById(order_id.get()).get();
                for (ProductDto productDto : products){
                    Product realProduct = productService.findById(productDto.getProduct_id()).get();
                    realProduct.setQuantity(realProduct.getQuantity()-productDto.getQuantity());
                    productService.save(realProduct);

                    OrderDetail realOrderDetail = new OrderDetail(currentOrder, realProduct, productDto.getQuantity());
                    orderDetailService.save(realOrderDetail);
                }
            }
            return new ResponseEntity<>(realOrder, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
