package com.codegym.casestudym4.controller;


import com.codegym.casestudym4.model.OrderDetail;
import com.codegym.casestudym4.model.Product;
import com.codegym.casestudym4.model.User;
import com.codegym.casestudym4.model.dtoCart.OrderDto;
import com.codegym.casestudym4.model.orderDTO.OrderDetailDTO;
import com.codegym.casestudym4.service.order.IOrdersService;
import com.codegym.casestudym4.service.orderdetail.IOrderDetailService;
import com.codegym.casestudym4.service.product.IProductService;
import com.codegym.casestudym4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderDetailService detailService;

    @Autowired
    private IOrdersService ordersService;

    @GetMapping
    public ResponseEntity<Page<OrderDetail>> showOrderDetails(@PageableDefault(size = 5) Pageable pageable){
        return new ResponseEntity<>(detailService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<?> showOrderDetailsAdmin(){
        return new ResponseEntity<>(detailService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDetail> save(@RequestBody OrderDetailDTO orderDetailDTO){
        for (int i=0;i<orderDetailDTO.getProduct().size();i++){

            Product product = productService.findById(orderDetailDTO.getProduct().get(i).getId()).get();

            Long quantity = orderDetailDTO.getQuantity().get(i);

            setQuantityProduct(product,quantity);

            detailService.save(new OrderDetail(orderDetailDTO.getOrders(),product, quantity));
        }

        setTotalSpent(orderDetailDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    set Total Spent User
    private void setTotalSpent(OrderDetailDTO orderDetailDTO) {
        User user = ordersService.findById(orderDetailDTO.getOrders().getId()).get().getUser();
        user.setTotalSpent(user.getTotalSpent() + orderDetailDTO.getTotalSpent());
        userService.save(user);
    }
// set Quantity Product
    private void setQuantityProduct(Product product,Long quantity){
        product.setQuantity(product.getQuantity()-quantity);
        productService.save(product);
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

    @GetMapping("/{id}")
    public ResponseEntity<Page<OrderDetail>> getOrderDetail(@PageableDefault(size = 5) Pageable pageable,@PathVariable Long id){
        Page<OrderDetail> orderDetail = detailService.findAllByOrders(pageable,ordersService.findById(id).get());
        if (orderDetail.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetail,HttpStatus.OK);

    }

}
