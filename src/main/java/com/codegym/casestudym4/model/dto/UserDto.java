package com.codegym.casestudym4.model.dto;

import com.codegym.casestudym4.constraint.UniqueUsername;
import com.codegym.casestudym4.model.Role;
import com.codegym.casestudym4.model.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserDto {
    private Long id;

    @UniqueUsername
    @Size(min = 3, max = 50)
    @NotEmpty
    private String username;

    @Size(min = 6, max = 20)
    private String password;

    @Size(min = 3, max = 50)
    private String fullName;

    Set<Role> roles;

    private Double totalSpent;

    public UserDto() {
        this.totalSpent = 0.0;
    }

    public UserDto(Long id, String username, String password, String fullName, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.roles = roles;
    }

    public UserDto(String username, String password, String fullName, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.roles = roles;
    }

    public static UserDto build(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getFullName(), user.getRoles());
    }

    public static User toPojo(UserDto userDto){
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getFullName(), userDto.getRoles());
    }
}
