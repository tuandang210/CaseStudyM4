package com.codegym.casestudym4.service.brand;

import com.codegym.casestudym4.model.Brand;
import com.codegym.casestudym4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandService extends IGeneralService<Brand> {
    Page<Brand> findAllPageable(Pageable pageable);

    Page<Brand> findAllByNameContaining(Pageable pageable, String name);
}
