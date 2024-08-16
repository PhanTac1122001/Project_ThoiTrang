package com.n3.project_thoitrang.service.impl;

import com.n3.project_thoitrang.model.entity.Size;
import com.n3.project_thoitrang.repository.ISizeRepository;
import com.n3.project_thoitrang.repository.impl.SizeRepositoryImpl;
import com.n3.project_thoitrang.service.ISizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements ISizeService {
    private final ISizeRepository sizeRepository;
    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public boolean save(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public void deleteById(Long id) {
sizeRepository.deleteById(id);
    }

    @Override
    public Size findById(Long id) {
        return sizeRepository.findById(id);
    }
}
