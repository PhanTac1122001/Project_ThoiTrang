package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.model.entity.Category;
import com.n3.project_thoitrang.model.entity.Image;

import java.util.List;

public interface IImageService {
    List<Image> findAll();

    // Thêm mới hoặc update
    void save(Image image);

    // Xoá by id
    void deleteById(Long id);

    // Tìm kiếm
    Image findById(Long id);
}
