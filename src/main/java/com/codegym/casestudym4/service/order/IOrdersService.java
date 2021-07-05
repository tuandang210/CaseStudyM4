package com.codegym.casestudym4.service.order;

import com.codegym.casestudym4.model.Orders;
import com.codegym.casestudym4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrdersService extends IGeneralService<Orders> {
    Page<Orders> findAllPageable(Pageable pageable);
}
