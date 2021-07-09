package com.codegym.casestudym4.controller;

import com.codegym.casestudym4.model.*;
import com.codegym.casestudym4.service.brand.IBrandService;
import com.codegym.casestudym4.service.color.IColorService;
import com.codegym.casestudym4.service.jwt.JwtService;
import com.codegym.casestudym4.service.product.IProductService;
import com.codegym.casestudym4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class UserProductController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private IColorService colorService;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ModelAndView showIndex(){
        return new ModelAndView("/index");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView showIndexUser(){
        return new ModelAndView("/indexUser");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView adminPage(){
        return new ModelAndView("/admins/adminPag");
    }

    @GetMapping("/user-product/list")
    public ModelAndView showAllCars(@PageableDefault Pageable pageable){
        Page<Product> products = productService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/allProduct", "products", products);
        modelAndView.addObject("brands", brandService.findAll());
        modelAndView.addObject("colors", colorService.findAll());
        return modelAndView;
    }

    @GetMapping("/user-product")
    public ResponseEntity<Page<Product>> findProductAll(@PageableDefault Pageable pageable){
        Page<Product> products = productService.findAll(pageable);
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("user-product/{id}")
    public ModelAndView findCarsById(@PathVariable Long id){
        Optional<Product> productOptional= productService.findById(id);
        if(!productOptional.isPresent()){
            return new ModelAndView("/error-404");
        }
        ModelAndView modelAndView = new ModelAndView("/show-car-detail-for-user", "product",productOptional.get());
        return modelAndView;
    }
    @PostMapping("/user-product11")
    public ResponseEntity<User> returnUsername(@RequestBody Long id){
      Optional<User> userOptional = userService.findById(id);
      if(!userOptional.isPresent()){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @ModelAttribute("brands")
    public Iterable<Brand>brands(){
        return brandService.findAll();
    }

    @ModelAttribute("colors")
    public Iterable<Color>colors(){
        return colorService.findAll();
    }

}
