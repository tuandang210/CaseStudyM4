package com.codegym.casestudym4.service.order;

import com.codegym.casestudym4.model.Comment;
import com.codegym.casestudym4.model.Orders;
import com.codegym.casestudym4.repository.IOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdersService implements IOrdersService {
    @Autowired
    private IOrdersRepository orderRepository;

    @Override
    public Iterable<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Page<Orders> findAllPageable(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
