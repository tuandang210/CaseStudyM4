package com.codegym.casestudym4.controller;


import com.codegym.casestudym4.model.Color;
import com.codegym.casestudym4.service.color.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/colors")
public class ColorController {

    @Autowired
    private IColorService colorService;

    @GetMapping
    public ResponseEntity<?> colors(){
        return new ResponseEntity<>(colorService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Color color){
        return new ResponseEntity<>(colorService.save(color),HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id){
        colorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Color color){
        return new ResponseEntity<>(colorService.save(color),HttpStatus.OK);
    }

}
