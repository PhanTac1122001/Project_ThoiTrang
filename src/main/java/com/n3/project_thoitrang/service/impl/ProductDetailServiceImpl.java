package com.n3.project_thoitrang.service.impl;

import com.n3.project_thoitrang.model.entity.Product_Detail;
import com.n3.project_thoitrang.repository.IProductDetailRepository;
import com.n3.project_thoitrang.repository.IProductRepository;
import com.n3.project_thoitrang.service.IProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements IProductDetailService {
    @Autowired
    private final IProductDetailRepository productDetailRepository;
    @Override
    public List<Product_Detail> findAll() {
        return productDetailRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
    productDetailRepository.deleteById(id);
    }

    @Override
    public boolean save(Product_Detail product_detail) {
        return productDetailRepository.save(product_detail);
    }

    @Override
    public Product_Detail findById(Long id) {
        return productDetailRepository.findById(id);
    }
}
