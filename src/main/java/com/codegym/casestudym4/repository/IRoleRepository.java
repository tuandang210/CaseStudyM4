package com.codegym.casestudym4.repository;

import com.codegym.casestudym4.model.Role;
import com.codegym.casestudym4.model.enumeration.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
