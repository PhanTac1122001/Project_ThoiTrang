package com.n3.project_thoitrang.service.impl;
import com.n3.project_thoitrang.model.entity.Category;
import com.n3.project_thoitrang.repository.ICategoryRepository;
import com.n3.project_thoitrang.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);

    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public Category findById(Long id) {
       return categoryRepository.findById(id);
    }
}
