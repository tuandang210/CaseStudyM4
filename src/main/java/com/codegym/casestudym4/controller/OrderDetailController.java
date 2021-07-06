package com.codegym.casestudym4.controller;


import com.codegym.casestudym4.model.OrderDetail;
import com.codegym.casestudym4.service.orderdetail.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailController {

    @Autowired
    private IOrderDetailService detailService;

    @GetMapping
    public ResponseEntity<Page<OrderDetail>> showOrderDetails(@PageableDefault(size = 5) Pageable pageable){
        return new ResponseEntity<>(detailService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDetail> save(@RequestBody OrderDetail orderDetail){
        return new ResponseEntity<>(detailService.save(orderDetail),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<OrderDetail> edit(@RequestBody OrderDetail orderDetail){

        return new ResponseEntity<>(detailService.save(orderDetail),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDetail> delete(@PathVariable Long id){
        detailService.findById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
