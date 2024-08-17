package com.n3.project_thoitrang.repository;

import com.n3.project_thoitrang.model.entity.Color;

import java.util.List;

public interface IColorRepository {

    List<Color> findAll();

    boolean save(Color color);

    void deleteById(Long id);

    Color findById(Long id);
}
