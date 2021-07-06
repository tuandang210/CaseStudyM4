package com.codegym.casestudym4.service.role;

import com.codegym.casestudym4.model.Role;
import com.codegym.casestudym4.service.IGeneralService;

import java.util.Optional;

public interface IRoleService extends IGeneralService<Role> {
    Optional<Role> findByRoleName(String roleName);
}
