package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.model.entity.Product_Detail;

import java.util.List;

public interface IProductDetailService {
    List<Product_Detail> findAll();


    void deleteById(Long id);

    boolean save(Product_Detail product_detail);

    Product_Detail findById(Long id);
}
