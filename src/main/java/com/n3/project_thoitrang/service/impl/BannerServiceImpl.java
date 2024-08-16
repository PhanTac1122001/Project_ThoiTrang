package com.n3.project_thoitrang.service.impl;

import com.n3.project_thoitrang.model.entity.Banner;
import com.n3.project_thoitrang.repository.IBannerRepository;
import com.n3.project_thoitrang.service.IBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements IBannerService {
    @Autowired
    private IBannerRepository bannerRepository;
    @Override
    public List<Banner> findAll() {
        return bannerRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
bannerRepository.deleteById(id);
    }

    @Override
    public Banner findById(Long id) {
       return bannerRepository.findById(id);
    }
}
