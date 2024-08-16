package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.model.entity.Banner;

import java.util.List;

public interface IBannerService {
    List<Banner> findAll();


    void deleteById(Long id);



    Banner findById(Long id);
}
