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

public class CartController {

}
