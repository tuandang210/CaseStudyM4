package com.codegym.casestudym4.controller;

import com.codegym.casestudym4.model.Brand;
import com.codegym.casestudym4.service.brand.IBrandService;
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
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    @GetMapping("/list")
    public ModelAndView showList(@PageableDefault(size = 1000) Pageable pageable) {
        Page<Brand> brands = brandService.findAllPageable(pageable);
        if (brands.isEmpty()) {
            return new ModelAndView("/error-404");
        }
        return new ModelAndView("/brand/list", "brands", brands);
    }

    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        return new ResponseEntity<>(brandService.save(brand), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand brand) {
        Optional<Brand> brandOptional = brandService.findById(id);
        if (!brandOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        brand.setId(brandOptional.get().getId());
        return new ResponseEntity<>(brandService.save(brand), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Brand> deleteBrand(@PathVariable Long id) {
        Optional<Brand> brandOptional = brandService.findById(id);
        if (!brandOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        brandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> findById(@PathVariable Long id) {
        try {
            Optional<Brand> brandOptional = brandService.findById(id);
            if (!brandOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(brandOptional.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> search(@RequestParam(required = false) String search, @PageableDefault Pageable pageable){
        Page<Brand> brands;
        if (search == null) {
            search = "";
        }
        if (!search.isEmpty()) {
            brands = brandService.findAllByNameContaining(pageable, search);
        } else {
            brands = brandService.findAllPageable(pageable);
        }
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }
}
