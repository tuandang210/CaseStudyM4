package com.codegym.casestudym4.repository;

import com.codegym.casestudym4.model.Orders;
import com.codegym.casestudym4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Long> {

    @Query(nativeQuery = true
            ,value = "select max(orders.id) from orders where user_id = ?1")
    Optional<Long> findLastOrderIdByUserId(Long id);

    Iterable<Orders> findAllByUser(User user);
}
