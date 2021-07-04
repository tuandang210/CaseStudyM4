package com.codegym.casestudym4.repository;

import com.codegym.casestudym4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
    @Query(value = "select * from customers limit ?1 offset ?2", nativeQuery = true)
    List<Product> findAllUsingQueryForPagination(Integer limit, Integer offset);
}
