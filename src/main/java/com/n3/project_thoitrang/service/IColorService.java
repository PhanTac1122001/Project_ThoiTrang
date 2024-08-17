package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.model.entity.Color;
import com.n3.project_thoitrang.model.entity.Size;

import java.util.List;

public interface IColorService {
    List<Color> findAll();

    boolean save(Color color);

    void deleteById(Long id);

    Color findById(Long id);
}
