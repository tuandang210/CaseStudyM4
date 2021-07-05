package com.codegym.casestudym4.repository;

import com.codegym.casestudym4.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  IBrandRepository extends JpaRepository<Brand, Long> {
    Page<Brand> findAllByNameContains(Pageable pageable, String name);
}
