package com.codegym.casestudym4.controller;


import com.codegym.casestudym4.model.Image;
import com.codegym.casestudym4.service.Image.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private IImageService imageService;

    @GetMapping
    public ResponseEntity<?> images(){
        return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Image image){
        return new ResponseEntity<>(imageService.save(image),HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id){
        imageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Image image){
        return new ResponseEntity<>(imageService.save(image),HttpStatus.OK);
    }
}
