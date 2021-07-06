package com.codegym.casestudym4.model.dto;

import com.codegym.casestudym4.model.Role;
import com.codegym.casestudym4.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> roles;

    public UserPrincipal() {
    }

    public UserPrincipal(Long id, String username, String password, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrincipal user = (UserPrincipal) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    //convert a UserPrincipal from a User
    public static UserPrincipal build(User user){

        Set<Role> roles = user.getRoles();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
