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
    Product findById(Long proId);

    //phân trang, search
    List<Product> findAllProduct(int page, int size, String search);
    Long totalAllProduct(String search);
    // Sắp xếp theo Product tăng dần
    List<Product> findAllByOrderByProductNameAsc(int page,int size);
    // Sắp xếp theo Product giảm dần
    List<Product> findAllByOrderByProductNameDesc(int page,int size);

}
