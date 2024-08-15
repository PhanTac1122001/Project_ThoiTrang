package com.n3.project_thoitrang.repository;

import com.n3.project_thoitrang.model.entity.Category;

import java.util.List;

public interface ICategoryRepository {

    // 1. lấy về list để hiển thị
    List<Category> findAll();

    // 2. Tìm kiếm theo id để update lấy ra đối tượng thông qua id
    Category findById(Long id);

    // 3. thêm mới hoặc update
    boolean save(Category category);

    // 4. Xóa
    void deleteById(Long id);
}
