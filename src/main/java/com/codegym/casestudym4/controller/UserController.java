package com.codegym.casestudym4.controller;

import com.codegym.casestudym4.model.User;
import com.codegym.casestudym4.model.*;
import com.codegym.casestudym4.service.Image.IImageService;
import com.codegym.casestudym4.service.brand.IBrandService;
import com.codegym.casestudym4.service.color.IColorService;
import com.codegym.casestudym4.service.role.IRoleService;
import com.codegym.casestudym4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

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

    @GetMapping("/user")
    public ModelAndView userPage(){
        return new ModelAndView("/user/user");
    }

    @GetMapping("/admin")
    public ModelAndView adminPage(){
        return new ModelAndView("/admins/adminPag");
    }

    @GetMapping
    public ResponseEntity<Page<User>> showAllUsers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size){
        Page<User> users = userService.findAll(page, size);
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
