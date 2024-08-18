package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.model.entity.Banner;
import com.n3.project_thoitrang.model.entity.Color;

import java.util.List;

public interface IBannerService {
    List<Banner> findAll();


    void deleteById(Long id);

    boolean save(Banner banner);

    Banner findById(Long id);
}
