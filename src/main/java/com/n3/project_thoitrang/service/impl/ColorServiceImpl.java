package com.n3.project_thoitrang.service.impl;

import com.n3.project_thoitrang.model.entity.Color;
import com.n3.project_thoitrang.repository.IColorRepository;
import com.n3.project_thoitrang.service.IColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements IColorService {
    private final IColorRepository colorRepository;
    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public boolean save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public void deleteById(Long id) {
colorRepository.deleteById(id);
    }

    @Override
    public Color findById(Long id) {
        return colorRepository.findById(id);
    }
}
