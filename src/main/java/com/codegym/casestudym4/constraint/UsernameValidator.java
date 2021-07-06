package com.codegym.casestudym4.constraint;

import com.codegym.casestudym4.model.User;
import com.codegym.casestudym4.model.dto.UserDto;
import com.codegym.casestudym4.service.user.IUserService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

public class UsernameValidator implements Validator {
    private IUserService userService;

    public UsernameValidator(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;
        String username = userDto.getUsername();
        //check unique username
        if (username != null){
            Optional<User> user = userService.findByUsername(username.trim());
            if (user.isPresent() && user.get().getId() != userDto.getId()){
                errors.rejectValue("username", "user.duplicate", "already exists");
            }
        }
    }
}
