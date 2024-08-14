package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.model.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product getProductById(Long proId);
    boolean save(Product pro);
    boolean update(Product pro);
    boolean delete(Long proId);
    List<Product> findByName(String proName);

}
