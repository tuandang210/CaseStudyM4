package com.codegym.casestudym4.controller;

import com.codegym.casestudym4.model.Brand;
import com.codegym.casestudym4.model.Color;
import com.codegym.casestudym4.model.Image;
import com.codegym.casestudym4.model.Product;
import com.codegym.casestudym4.service.Image.IImageService;
import com.codegym.casestudym4.service.brand.IBrandService;
import com.codegym.casestudym4.service.color.IColorService;
import com.codegym.casestudym4.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private IColorService colorService;

    @ModelAttribute("brands")
    public Iterable<Brand>brands(){
            return brandService.findAll();
    }

     @ModelAttribute("images")
    public Iterable<Image>images(){
        return imageService.findAll();
    }

    @ModelAttribute("colors")
    public Iterable<Color>colors(){
        return colorService.findAll();
    }

    @GetMapping("/list")
    public ModelAndView showList(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/products/list");
        modelAndView.addObject("products",productService.findAll(pageable));
        return modelAndView;
    }
    @GetMapping("/show-cars")
    public ModelAndView showCars(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/allProduct");
        modelAndView.addObject("products",productService.findAll(pageable));
        return modelAndView;
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsingPagination(Pageable pageable){
        Page<Product> products = productService.findAll(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> createNew(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<BigInteger> imageListDelete=  imageService.findImageIdOfProductByID(id);

        productService.deleteProductsByIdUseProceduce(id);

        if(!imageListDelete.isEmpty()){
            for (BigInteger imageId : imageListDelete) {
                Long idDelete = imageId.longValue();
                imageService.delete(idDelete);
            }
        }
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getInfoProduct(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id,@RequestBody Product productEdit){
        Optional<Product> productOptional = productService.findById(productEdit.getId());
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(productEdit.getImageSet()==null&&productOptional.get().getImageSet()!=null){
            productEdit.setImageSet(productOptional.get().getImageSet());
         }
        productService.save(productEdit);
        return new ResponseEntity<>(productEdit, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchByProduct(@RequestParam Optional<String> search,Pageable pageable){
        if(search.isPresent()){
            Page<Product> productPage = productService.findAllByNameContaining(search.get(),pageable);
            return new ResponseEntity<>(productPage,HttpStatus.OK);
        }else {
            Page<Product> productPage = productService.findAll(pageable);
            return new ResponseEntity<>(productPage,HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/aaaaa/{name}")
    public ResponseEntity<?> getProduct(@PathVariable String name){
        return new ResponseEntity<>(productService.findProductByName(name).get(),HttpStatus.OK);
    }
}
