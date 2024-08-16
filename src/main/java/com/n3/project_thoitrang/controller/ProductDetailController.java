package com.n3.project_thoitrang.controller;

import com.n3.project_thoitrang.model.entity.Product_Detail;
import com.n3.project_thoitrang.service.IProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/list-product")
@RequiredArgsConstructor
public class ProductDetailController {
    private final IProductDetailService productDetailService;

    @GetMapping()
    public String listProduct() {
        return "admin/manage-product-detail";
    }



    @PostMapping("add-productdetail")
    public String addProductDetail(@Valid @ModelAttribute("productdetail") Product_Detail product_detail, Model model, BindingResult result) {
    if (result.hasErrors()){
        model.addAttribute("productdetail",product_detail);
        return "admin/add-product-detail";
    }else {
        Boolean bl=productDetailService.save(product_detail);
        if (bl){
            return "redirect:/manage-product-detail";
        }else {
            model.addAttribute("productdetail",product_detail);
           return "general/add-product-detail";
        }
    }
    }
}
