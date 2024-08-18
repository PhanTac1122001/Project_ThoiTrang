package com.n3.project_thoitrang.controller;

import com.n3.project_thoitrang.model.entity.Category;
import com.n3.project_thoitrang.model.entity.Product;
import com.n3.project_thoitrang.service.ICategoryService;
import com.n3.project_thoitrang.service.IProductDetailService;
import com.n3.project_thoitrang.service.IProductService;
import com.n3.project_thoitrang.service.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller // nơi tiếp nhận
@RequestMapping()
@RequiredArgsConstructor
public class ProductController {
    private final ICategoryService categoryService;

    private final IProductService productService;
    private final IProductDetailService productDetailService;
    private final UploadFile uploadFile;

    @GetMapping(value = { "/manage-Product"})
    public String listProduct(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/manage-product";
    }





    @PostMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute("products") Product pro, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("products", pro);
            return "general/add-product";
        }else {
            Boolean bl = productService.save(pro);
            if (bl) {
                return "redirect:/manage-Product";
            }else {
                model.addAttribute("pro",pro);
                model.addAttribute("error","inset is false");
                return "general/add-product";
            }
        }
    }
    @GetMapping("/add")
    public String doAddProduct(Model model) {
        model.addAttribute("products", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "general/add-product";
    }


    @GetMapping("deleteProduct/{product_id}")
    public String deleteProduct(@PathVariable Long product_id, Model model ){
        productService.delete(product_id);
        model.addAttribute("products", productService.findAll());
        return "/admin/manage-product";
    }

    @GetMapping("/editProduct/{id}")
    public String viewEditProduct(@PathVariable Long id, Model model)
    {
        Product p = productService.findById(id);
        model.addAttribute("p", p);
        model.addAttribute("categories", categoryService.findAll());

        return "general/edit-product";
    }

    @PostMapping("/editProduct/{id}")
    public String doUpdate(@PathVariable Long id, @Valid @ModelAttribute("p") Product p, BindingResult result, Model model){
        if (result.hasErrors())
        {
            model.addAttribute("p", p);
            model.addAttribute("categories", categoryService.findAll());
            return "general/edit-product";
        }
        productService.save(p);
        return "redirect:/manage-Product";

    }

    @GetMapping("/detailProduct/{id}")
    public String viewDetailEditProduct(@PathVariable Long id, Model model)
    {
        Product p = productService.findById(id);
        model.addAttribute("p", p);
        model.addAttribute("product-detail", productDetailService.findAll());

        return "admin/manage-product-detail";
    }
}
