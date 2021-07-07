package com.codegym.casestudym4.controller;

import com.codegym.casestudym4.model.Role;
import com.codegym.casestudym4.model.dto.UserDto;
import com.codegym.casestudym4.model.jwt.JwtResponse;
import com.codegym.casestudym4.model.User;
import com.codegym.casestudym4.service.jwt.JwtService;
import com.codegym.casestudym4.service.role.IRoleService;
import com.codegym.casestudym4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;


    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String logout) {
        ModelAndView mav = new ModelAndView("/user/login");
        if (logout != null){
            mav.addObject("logoutMsg", "You have been logged out successfully");
        }
        return mav;
    }

    @GetMapping("/user")
    public ModelAndView userPage(){
        ModelAndView mav = new ModelAndView("/user/user");
        return mav;
    }

    @GetMapping("/admin")
    public ModelAndView adminPage(){
        ModelAndView mav = new ModelAndView("adminPag");
        return mav;
    }

    @GetMapping("/forbidden")
    public ModelAndView accessDenied(){
        ModelAndView mav = new ModelAndView("/user/access-denied");
        return mav;
    }



    @PostMapping("/login/api")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getFullName(), userDetails.getAuthorities()));
    }


    @PostMapping("/register/api")
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto){

        //assign role
        if (userDto.getRoles() == null) {
            userDto.setRoles(defaultRole());
        }
        userService.save(UserDto.toPojo(userDto));

        UserDto result = UserDto.build(userService.findByUsername(userDto.getUsername()).get());

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    public Set<Role> defaultRole(){
        Role role_user = roleService.findByRoleName("ROLE_USER").get();
        Set<Role> defaultRole = new HashSet<>();
        defaultRole.add(role_user);
        return defaultRole;
    }

}
