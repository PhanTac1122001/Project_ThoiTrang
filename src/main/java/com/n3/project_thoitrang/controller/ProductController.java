package com.n3.project_thoitrang.controller;

import com.n3.project_thoitrang.model.entity.Product;
import com.n3.project_thoitrang.service.ICategoryService;
import com.n3.project_thoitrang.service.IProductService;
import com.n3.project_thoitrang.service.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller // nơi tiếp nhận
@RequestMapping()
@RequiredArgsConstructor
public class ProductController {
    private final ICategoryService categoryService;

    private final IProductService productService;

    private final UploadFile uploadFile;

    @GetMapping(value = { "manage-Product"})
    public String listProduct(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/manage-product";
    }

    @PostMapping("addProduct")
    public String addProduct(@Valid @ModelAttribute("pro") Product pro, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("pro", pro);
            return "general/add-product";
        }else {
            Boolean bl = productService.save(pro);
            if (bl) {
                return "redirect:/manage-product";
            }else {
                model.addAttribute("pro",pro);
                model.addAttribute("error","inset is false");
                return "general/add-product";
            }
        }
    }
    @GetMapping("add")
    public String doAddProduct(Model model) {
        model.addAttribute("products", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "general/add-product";
    }


//    @GetMapping("delete/{id}")
//    public String delete(@PathVariable Long id ){
//        productService.delete(id);
//        return "redirect:/manage-product";
//    }

}
