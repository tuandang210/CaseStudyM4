package com.codegym.casestudym4.repository;

import com.codegym.casestudym4.model.Role;
import com.codegym.casestudym4.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);

    Page<User> findAllByUsernameContaining(String username, Pageable pageable);

    @Query(nativeQuery = true
            , value = "select users.id, users.full_name, users.username, users.password, users.total_spent from users, users_roles, roles where users.id = users_roles.user_id and roles.id = users_roles.role_id and roles.role_name like ?1")
    Page<User> findAllByRoleName(String roleName, Pageable pageable);

    Optional<User> findByUsername(String username);
}
