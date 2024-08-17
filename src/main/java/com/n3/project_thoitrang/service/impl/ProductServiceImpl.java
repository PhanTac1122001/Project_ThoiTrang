package com.n3.project_thoitrang.service.impl;
import com.n3.project_thoitrang.model.entity.Category;
import com.n3.project_thoitrang.model.entity.Product;
import com.n3.project_thoitrang.repository.ICategoryRepository;
import com.n3.project_thoitrang.repository.IProductRepository;
import com.n3.project_thoitrang.service.ICategoryService;
import com.n3.project_thoitrang.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long proId) {
        return productRepository.getProductById(proId);
    }

    @Override
    public boolean save(Product pro) {
        return productRepository.save(pro);
    }

    @Override
    public boolean update(Product pro) {
        return productRepository.save(pro);
    }

    @Override
    public boolean delete(Long proId) {
        return productRepository.delete(proId);
    }

    @Override
    public List<Product> findByName(String proName) {
        return productRepository.findByName(proName);
    }

    @Override
    public Product findById(Long proId) {
        return productRepository.findById(proId);
    }

    @Override
    public List<Product> findAllProduct(int page, int size, String search) {
        return productRepository.findAllProduct(page, size, search);
    }

    @Override
    public Long totalAllProduct(String search) {
        return productRepository.totalAllProduct(search);
    }

    @Override
    public List<Product> findAllByOrderByProductNameAsc(int page, int size) {
        return productRepository.findAllByOrderByProductNameAsc(page, size);
    }

    @Override
    public List<Product> findAllByOrderByProductNameDesc(int page, int size) {
        return productRepository.findAllByOrderByProductNameDesc(page, size);
    }
}
