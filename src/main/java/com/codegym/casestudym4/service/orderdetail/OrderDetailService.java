package com.codegym.casestudym4.service.orderdetail;

import com.codegym.casestudym4.model.OrderDetail;
import com.codegym.casestudym4.model.Orders;
import com.codegym.casestudym4.repository.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    private IOrderDetailRepository detailRepository;

    @Override
    public Iterable<OrderDetail> findAll() {
        return detailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return detailRepository.findById(id);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return detailRepository.save(orderDetail);
    }

    @Override
    public void delete(Long id) {
        detailRepository.deleteById(id);
    }

    @Override
    public Page<OrderDetail> findAll(Pageable pageable) {
        return detailRepository.findAll(pageable);
    }

    @Override
    public Page<OrderDetail> findAllByOrders(Pageable pageable, Orders orders) {
        return detailRepository.findAllByOrders(pageable,orders);
    }
}
