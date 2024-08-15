package com.n3.project_thoitrang.repository;

import com.n3.project_thoitrang.model.entity.Product;
import com.n3.project_thoitrang.model.entity.Product_Detail;

import java.util.List;

public interface IProDetailsRepo {
    List<Product_Detail> findAll();
    Product getIdProduct_Details(Long proId);
    boolean save(Product pro);
    boolean update(Product pro);
    boolean delete(Long proId);
    List<Product> findByName(String proName);
    Product findById(Long id);
}
