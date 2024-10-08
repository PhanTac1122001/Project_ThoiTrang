package com.n3.project_thoitrang.controller;
import com.n3.project_thoitrang.dto.CategoryDto;
import com.n3.project_thoitrang.model.entity.Category;
import com.n3.project_thoitrang.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller // nơi tiếp nhận
@RequestMapping
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("/category")
    public String findAll(Model model)
    {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/manage-category";
    }

    @GetMapping("/add_category")
    public String viewAdd(Model model)
    {
        model.addAttribute("category", new CategoryDto());
        return "general/add-category";
    }

    @PostMapping("/add_category")
    public String doAddCategory(@Valid @ModelAttribute CategoryDto categoryDto, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("category", categoryDto);
            return "general/add-category";
        }

        categoryService.save(Category.builder()
                .categoryId(categoryDto.getId())
                .categoryName(categoryDto.getName())
                .description(categoryDto.getDescription())
                .status(categoryDto.getStatus())
                .build());
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String viewEdit(@PathVariable Long id, Model model)
    {
        Category cate = categoryService.findById(id);
        model.addAttribute("cate", cate);

        return "general/edit-category";
    }

    @PostMapping("/edit")
    public String doUpdate(@Valid @ModelAttribute("cate") Category cate, BindingResult result, Model model){
        if (result.hasErrors())
        {
            model.addAttribute("cate", cate);
            return "general/edit-category";
        }
        categoryService.save(cate);
        return "redirect:/admin/manage-category";

    }


    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id)
    {
        categoryService.deleteById(id);
        return "redirect:/admin/manage-category";
    }


}
