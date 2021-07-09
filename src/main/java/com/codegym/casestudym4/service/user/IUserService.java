package com.codegym.casestudym4.service.user;


import com.codegym.casestudym4.model.User;
import com.codegym.casestudym4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Page<User> findAll(Integer page, Integer size);

    Page<User> findAllByUsernameContaining(String username, Integer page, Integer size);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Page<User> findAllByRoleName(String roleName, Integer page, Integer size);
}
