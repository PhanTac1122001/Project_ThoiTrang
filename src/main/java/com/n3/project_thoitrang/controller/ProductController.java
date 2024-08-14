package com.n3.project_thoitrang.controller;

import com.n3.project_thoitrang.repository.IProductRepository;
import com.n3.project_thoitrang.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // nơi tiếp nhận
@RequestMapping()
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

}
