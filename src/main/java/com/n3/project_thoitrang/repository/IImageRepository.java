package com.n3.project_thoitrang.repository;

import com.n3.project_thoitrang.model.entity.Category;
import com.n3.project_thoitrang.model.entity.Image;

import java.util.List;

public interface IImageRepository {


    // 1. lấy về list để hiển thị
    List<Image> findAll();

    // 2. Tìm kiếm theo id để update lấy ra đối tượng thông qua id
    Image findById(Long id);

    // 3. thêm mới hoặc update
    boolean save(Image image);

    // 4. Xóa
    void deleteById(Long id);
}
