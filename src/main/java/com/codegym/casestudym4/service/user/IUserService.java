package com.codegym.casestudym4.service.user;


import com.codegym.casestudym4.model.User;
import com.codegym.casestudym4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService extends IGeneralService<User> {
    Page<User> findAll(Integer page, Integer size);

    Page<User> findAllByUsernameContaining(String username, Integer page, Integer size);

    Optional<User> findByUsername(String username);

    Page<User> findAllByRoleName(String roleName, Integer page, Integer size);
}
