package com.n3.project_thoitrang.service.impl;

import com.n3.project_thoitrang.dto.ProductDetailDto;
import com.n3.project_thoitrang.model.entity.Color;
import com.n3.project_thoitrang.model.entity.Product;
import com.n3.project_thoitrang.model.entity.Product_Detail;
import com.n3.project_thoitrang.model.entity.Size;
import com.n3.project_thoitrang.repository.IProductDetailRepository;
import com.n3.project_thoitrang.repository.IProductRepository;
import com.n3.project_thoitrang.service.IColorService;
import com.n3.project_thoitrang.service.IProductDetailService;
import com.n3.project_thoitrang.service.IProductService;
import com.n3.project_thoitrang.service.ISizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements IProductDetailService {


    @Autowired
    private final IProductDetailRepository productDetailRepository;
    private final IColorService colorService;
    private final ISizeService sizeService;
    private final IProductService productService;
    // Add your ImageService dependency here
    // private final ImageService imageService;

    @Override
    public List<Product_Detail> findAll() {
        return productDetailRepository.findAll();
    }

    @Override
    public List<Product_Detail> findByProductId(Long id) {
        return productDetailRepository.findByProductId(id);
    }

    @Override
    public void deleteById(Long id) {
        productDetailRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(ProductDetailDto productDetailDto, List<MultipartFile> images) {
        try {
            Size size = sizeService.findById(productDetailDto.getSize());
            Color color = colorService.findById(productDetailDto.getColor());
            Product product = productService.findById(productDetailDto.getProduct());

            Product_Detail productDetail = Product_Detail.builder()
                    .unitPrice(productDetailDto.getUnitPrice())
                    .color(color)
                    .size(size)
                    .stockQuantity(productDetailDto.getStockQuantity())
                    .product(product)
                    .build();

            // Handle image saving
            // ... (Use your ImageService here)

            productDetailRepository.save(productDetail, images); // Pass the images
        } catch (Exception e) {
            // Handle exceptions (e.g., EntityNotFoundException)
            // Log the error
            // ...
        }
    }

    @Override
    public Product_Detail findById(Long id) {
        return productDetailRepository.findById(id);
    }
}
