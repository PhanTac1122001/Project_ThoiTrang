package com.n3.project_thoitrang.service.impl;

import com.n3.project_thoitrang.model.entity.Image;
import com.n3.project_thoitrang.repository.ICartRepository;
import com.n3.project_thoitrang.repository.IImageRepository;
import com.n3.project_thoitrang.service.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService {
    @Autowired
    private IImageRepository iImageRepository;
    @Override
    public List<Image> findAll() {
        return iImageRepository.findAll();
    }

    @Override
    public void save(Image image) {
iImageRepository.save(image);
    }

    @Override
    public void deleteById(Long id) {
iImageRepository.deleteById(id);
    }

    @Override
    public Image findById(Long id) {
        return iImageRepository.findById(id);
    }
}
