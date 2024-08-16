package com.n3.project_thoitrang.repository;

import com.n3.project_thoitrang.model.entity.Product_Detail;
import com.n3.project_thoitrang.model.entity.Shoping_Cart;

import java.util.List;

public interface IProductDetailRepository {
    List<Product_Detail> findAll();


    void deleteById(Long id);

    boolean save(Product_Detail product_detail);

    Product_Detail findById(Long id);
}
