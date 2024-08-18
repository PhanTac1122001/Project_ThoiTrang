package com.n3.project_thoitrang.controller;

import com.n3.project_thoitrang.dto.ProductDetailDto;
import com.n3.project_thoitrang.model.entity.*;
import com.n3.project_thoitrang.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class ProductDetailController {
    private final IProductDetailService productDetailService;
    private final IProductService productService;
    private final IColorService colorService;
    private final ISizeService sizeService;
    private final IImageService imageService;
    @GetMapping("/product-detail/{id}")
    public String productDetail(Model model, @PathVariable Long id) {
        model.addAttribute("product",productService.findById(id));
        model.addAttribute("productDetails", productDetailService.findByProductId(id));
        return "admin/manage-product-detail";
    }
    @GetMapping(value = { "/list"})
    public String showProduct(Model model) {

        model.addAttribute("productDetails", productDetailService.findAll());
        return "user/list-product";
    }


    @GetMapping("/add-product-detail/{id}")
    public String doAddProductDetail(Model model, @PathVariable("id") Long id) {
        Product product = productService.findById(id); // Fetch the product

        ProductDetailDto productDetailDto = ProductDetailDto.builder()
                .product(product.getProductId()) // Set the product ID
                .build();

        model.addAttribute("product", product);
        model.addAttribute("size", sizeService.findAll());
        model.addAttribute("color", colorService.findAll());
        model.addAttribute("productDetails", productDetailDto); // Add the DTO to the model

        return "general/add-admin-detail";
    }
    //    if(result.hasErrors()){
//         UUID uuid=UUID.randomUUID();
//        String sku = uuid.toString().substring(0, 8);
//        model.addAttribute("size",sizeService.findAll());
//        model.addAttribute("color",colorService.findAll());
//        model.addAttribute("productDetail",productDetailService.findByProductId(productDetailId));
//        model.addAttribute("product",productDetailDto.getProduct());
//        model.addAttribute("sku",sku);
//        model.addAttribute("image",imageService.findAll());
//        model.addAttribute("productDetailDto",productDetailDto);
//        return "admin/manage-product-detail";
//    }else {
//        productDetailService.save(productDetailDto,images);
//    }
//    return "redirect:/manage-product-detail";
//    }
    @PostMapping("/add-productDetail")
    public String addProductDetail(@Valid @ModelAttribute("productDetails") ProductDetailDto productDetailDto,
                                   @RequestParam("image") List<MultipartFile> images,
                                   Model model, BindingResult result) {

        if (result.hasErrors()) {
            // Handle form validation errors
            return "general/add-admin-detail";
        }

        try {
            // Use your service to save the product detail
            productDetailService.saveOrUpdate(productDetailDto, images);

            return "redirect:/product-detail/"+productDetailDto.getProduct();
        } catch (Exception e) {
            // Handle errors
            model.addAttribute("errorMessage", "Error saving product details.");
            return "general/add-admin-detail";
        }
    }

}
