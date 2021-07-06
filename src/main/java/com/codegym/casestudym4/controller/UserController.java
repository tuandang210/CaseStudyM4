package com.codegym.casestudym4.controller;

import com.codegym.casestudym4.constraint.UsernameValidator;
import com.codegym.casestudym4.model.Role;
import com.codegym.casestudym4.model.User;
import com.codegym.casestudym4.model.dto.UserDto;
import com.codegym.casestudym4.service.role.IRoleService;
import com.codegym.casestudym4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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


    @PostMapping("/api")
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto){

        //assign role
        if (userDto.getRoles() == null) {
            userDto.setRoles(defaultRole());
        }
        userService.save(UserDto.toPojo(userDto));
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
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


    public Set<Role> defaultRole(){
        Role role_user = roleService.findByRoleName("ROLE_USER").get();
        Set<Role> defaultRole = new HashSet<>();
        defaultRole.add(role_user);
        return defaultRole;
    }
}
