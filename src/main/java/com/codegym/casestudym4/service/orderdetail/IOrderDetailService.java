package com.codegym.casestudym4.service.orderdetail;

import com.codegym.casestudym4.model.OrderDetail;
import com.codegym.casestudym4.model.Orders;
import com.codegym.casestudym4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderDetailService extends IGeneralService<OrderDetail> {
    Page<OrderDetail> findAll(Pageable pageable);

    Page<OrderDetail> findAllByOrders(Pageable pageable, Orders orders);


}
