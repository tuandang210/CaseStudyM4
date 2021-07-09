package com.codegym.casestudym4.service.order;

import com.codegym.casestudym4.model.Orders;
import com.codegym.casestudym4.model.User;
import com.codegym.casestudym4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IOrdersService extends IGeneralService<Orders> {
    Page<Orders> findAllPageable(Pageable pageable);

    Optional<Long> findLastOrderIdByUserId(Long id);

    Iterable<Orders> findAllByUser(User user);

}
