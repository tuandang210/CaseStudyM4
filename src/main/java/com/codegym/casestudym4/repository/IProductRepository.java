package com.codegym.casestudym4.repository;

import com.codegym.casestudym4.model.Image;
import com.codegym.casestudym4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    @Query(value = "select * from products limit ?1 offset ?2", nativeQuery = true)
    List<Product> findAllUsingQueryForPagination(Integer limit, Integer offset);

    @Transactional
    @Modifying
    @Query(value = "Call Delete_Product(?1)",nativeQuery = true)
    void deleteProductsByIdUseProceduce(Long id);

    Optional<Product> findProductByName(String name);

}
