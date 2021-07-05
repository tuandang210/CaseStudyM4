package com.codegym.casestudym4.service.brand;

import com.codegym.casestudym4.model.Brand;
import com.codegym.casestudym4.repository.IBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private IBrandRepository brandRepository;

    @Override
    public Iterable<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }

    public Page<Brand> findAllPageable(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    @Override
    public Page<Brand> findAllByNameContaining(Pageable pageable, String name) {
        return brandRepository.findAllByNameContains(pageable, name);
    }

}
