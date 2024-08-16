package com.n3.project_thoitrang.repository;

import com.n3.project_thoitrang.model.entity.Color;
import com.n3.project_thoitrang.model.entity.Size;

import java.util.List;

public interface ISizeRepository {
    List<Size> findAll();

    boolean save(Size size);

    void deleteById(Long id);

    Size findById(Long id);
}
