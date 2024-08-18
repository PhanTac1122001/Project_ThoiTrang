package com.n3.project_thoitrang.repository;

import com.n3.project_thoitrang.model.entity.Product_Detail;
import com.n3.project_thoitrang.model.entity.Shoping_Cart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductDetailRepository {
    List<Product_Detail> findAll();

    List<Product_Detail> findByProductId(Long id);

    void deleteById(Long id);

    void save(Product_Detail product_detail, List<MultipartFile> images);

    Product_Detail findById(Long id);
}
