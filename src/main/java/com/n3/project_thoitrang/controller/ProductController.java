package com.n3.project_thoitrang.controller;

import com.n3.project_thoitrang.model.entity.Category;
import com.n3.project_thoitrang.model.entity.Product;
import com.n3.project_thoitrang.service.ICategoryService;
import com.n3.project_thoitrang.service.ILoginRegisterService;
import com.n3.project_thoitrang.service.IProductService;
import com.n3.project_thoitrang.service.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller // nơi tiếp nhận
@RequestMapping()
@RequiredArgsConstructor
public class ProductController {
    private final ICategoryService categoryService;

    private final IProductService productService;

    private final UploadFile uploadFile;

    private final HttpSession session;

//    @GetMapping(value = { "/manage-Product"})
//    public String listProduct(Model model) {
//        model.addAttribute("products", productService.findAll());
//        return "admin/manage-product";
//    }

    @GetMapping(value = { "/list"})
    public String showProduct() {
        return "user/list-product";
    }



    @PostMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute("products") Product pro, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("products", pro);
            return "general/add-product";
        }else {
            Boolean bl = productService.save(pro);
            if (bl) {
                return "redirect:manage-product";
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
        return "redirect:/admin/manage-product";

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
        return "redirect:/admin/manage-product";

    }


//    -----



    @GetMapping("/admin/manage-product")
    public String viewProduct(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            @RequestParam(name = "search", defaultValue = "") String search,
            Model model
    )
    {
        // set session
        session.setAttribute("active_product", "products");
        // set list user pagination
        model.addAttribute("productList", productService.findAllProduct(page, size, search));
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("search", search);

        // totalPages
        Double totalPages = Math.ceil((double) productService.totalAllProduct(search) / size);
        model.addAttribute("totalPages", totalPages);
        return "admin/manage-product";
    }
    @GetMapping("admin/manage-product/sortProductList")
    public String sortByName(Model model,
                             @RequestParam(name = "page", defaultValue = "0") Integer page,
                             @RequestParam(name = "size", defaultValue = "5") Integer size,
                             @RequestParam(name = "search", defaultValue = "") String search,
                             @RequestParam(value = "sort", defaultValue = "asc") String sort) {
        List<Product> product;
        if ("desc".equalsIgnoreCase(sort)) {
            product = productService.findAllByOrderByProductNameDesc(page,size);
        } else {
            product = productService.findAllByOrderByProductNameAsc(page,size);
        }
        model.addAttribute("productList", product);
        model.addAttribute("sort", sort);
        model.addAttribute("page",0);
        model.addAttribute("size",5);
        model.addAttribute("search", search);

        // totalPages
        Double totalPages = Math.ceil((double) productService.totalAllProduct(search) / size);
        model.addAttribute("totalPages", totalPages);
        return "admin/manage-product";
    }

}
