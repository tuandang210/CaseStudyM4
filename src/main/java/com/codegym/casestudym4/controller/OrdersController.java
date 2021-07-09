package com.codegym.casestudym4.controller;

import com.codegym.casestudym4.model.Orders;
import com.codegym.casestudym4.service.order.IOrdersService;
import com.codegym.casestudym4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;


    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public ModelAndView showList(@PageableDefault(size = 1000) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/order/list");
        Page<Orders> ordersPage = ordersService.findAllPageable(pageable);
        if(ordersPage.isEmpty()){
            return new ModelAndView("/error-404");
        }
        modelAndView.addObject("ordersPage", ordersPage);
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<Long> createBrand(@RequestBody Orders orders) {
        ordersService.save(orders);
        return new ResponseEntity<>(ordersService.findLastOrderIdByUserId(orders.getUser().getId()).get(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrders(@PathVariable Long id, @RequestBody Orders orders) {
        Optional<Orders> ordersOptional = ordersService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orders.setId(ordersOptional.get().getId());
        return new ResponseEntity<>(ordersService.save(orders), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        Iterable<Orders> orders = ordersService.findAllByUser(userService.findById(id).get());
        if (!orders.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

}
