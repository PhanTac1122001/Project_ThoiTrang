package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.dto.ProductDetailDto;
import com.n3.project_thoitrang.model.entity.Product_Detail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductDetailService {
    List<Product_Detail> findAll();

    List<Product_Detail> findByProductId(Long id);

    void deleteById(Long id);

    void saveOrUpdate(ProductDetailDto productDetailDto, List<MultipartFile> images);

    Product_Detail findById(Long id);
}
