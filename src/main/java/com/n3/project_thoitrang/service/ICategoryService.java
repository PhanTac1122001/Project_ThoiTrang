package com.n3.project_thoitrang.service;
import com.n3.project_thoitrang.model.entity.Category;
import com.n3.project_thoitrang.model.entity.Product_Detail;

import java.util.List;

public interface ICategoryService {

    // lấy về list để hiển thị

    List<Category> findAll();

    // Thêm mới hoặc update
    void save(Category category);

    // Xoá by id
    void deleteById(Long id);

    // Tìm kiếm
    Category findById(Long id);

    List<Product_Detail> showAllProductDetails(Long categoryId);
}
