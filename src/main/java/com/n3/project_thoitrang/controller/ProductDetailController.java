package com.n3.project_thoitrang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list-product")
public class ProductDetailController {
@GetMapping()
    public String listProduct(){
    return "user/list-product";
}
}
