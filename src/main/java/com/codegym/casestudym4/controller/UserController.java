package com.codegym.casestudym4.controller;

import com.codegym.casestudym4.model.User;
import com.codegym.casestudym4.service.role.IRoleService;
import com.codegym.casestudym4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("/users")
@RequestMapping("")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;



    @GetMapping("/api")
    public ResponseEntity<Page<User>> showAllUsers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size){
        Page<User> users = userService.findAll(page, size);
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @DeleteMapping("/api/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
