package com.codegym.casestudym4.service.product;

import com.codegym.casestudym4.model.Product;
import com.codegym.casestudym4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    Page<Product> findAllByNameContaining(String name,Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    List<Product> findAll(int page, int size);

    @Query(value = "select * from products limit ?1 offset ?2", nativeQuery = true)
    List<Product> findAllUsingQueryForPagination(Integer limit, Integer offset);
}
