package com.codegym.casestudym4.repository;

import com.codegym.casestudym4.model.OrderDetail;
import com.codegym.casestudym4.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    Page<OrderDetail> findAll(Pageable pageable);

    Page<OrderDetail> findAllByOrders(Pageable pageable, Orders orders);
}
