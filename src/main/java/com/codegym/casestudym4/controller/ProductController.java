package com.codegym.casestudym4.controller;

import com.codegym.casestudym4.model.Brand;
import com.codegym.casestudym4.model.Product;
import com.codegym.casestudym4.service.brand.IBrandService;
import com.codegym.casestudym4.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IBrandService brandService;
    @ModelAttribute("brands")
    public Iterable<Brand>brands(){
        return brandService.findAll();
    }
    @GetMapping("/list")
    public ModelAndView showList(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/products/list");
        modelAndView.addObject("products",productService.findAll(pageable));
        return modelAndView;
    }
    @GetMapping()
    public ResponseEntity<List<Product>> getAllCustomerUsingPagination(@RequestParam int page, @RequestParam int size){
        List<Product> customers = productService.findAllUsingQueryForPagination(size, page);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchByProduct(@RequestParam Optional<String> search, Pageable pageable){
        if(search.isPresent()){
            Page<Product> productPage = productService.findAllByNameContaining(search.get(),pageable);
            return new ResponseEntity<>(productPage,HttpStatus.OK);
        }else {
            Page<Product> productPage = productService.findAll(pageable);
            return new ResponseEntity<>(productPage,HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("{/id}")
    public ResponseEntity<Product> createNew(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteSmartphone(@PathVariable Long id) {
        Optional<Product> smartphoneOptional = productService.findById(id);
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(id);
        return new ResponseEntity<>(smartphoneOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getInfoSmartPhone(@PathVariable Long id){
        Optional<Product> smartphoneOptional = productService.findById(id);
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(smartphoneOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> editSmartPhone(@PathVariable Long id,@RequestBody Product productEdit){
        Optional<Product> productOptional = productService.findById(productEdit.getId());
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.save(productEdit);
        return new ResponseEntity<>(productEdit, HttpStatus.OK);
    }


}
