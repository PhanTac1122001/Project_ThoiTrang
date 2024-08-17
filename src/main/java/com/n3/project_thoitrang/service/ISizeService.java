package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.model.entity.Size;

import java.util.List;

public interface ISizeService {
    List<Size> findAll();

    boolean save(Size size);

    void deleteById(Long id);

    Size findById(Long id);
}
