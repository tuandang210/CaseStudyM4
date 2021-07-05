package com.codegym.casestudym4.service.product;

import com.codegym.casestudym4.model.Product;
import com.codegym.casestudym4.repository.IProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService implements IProductService {
    private IProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageRequest);
        return products.getContent();
    }

    @Override
    public List<Product> findAllUsingQueryForPagination(Integer limit, Integer offset) {
        return productRepository.findAllUsingQueryForPagination(limit,offset);
    }

    @Override
    public Page<Product> findAllByNameContaining(String name,Pageable pageable) {
        return productRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        productOptional.ifPresent(product -> productRepository.delete(product));
    }


}
