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
        return Collections.emptyList();
    }




}
