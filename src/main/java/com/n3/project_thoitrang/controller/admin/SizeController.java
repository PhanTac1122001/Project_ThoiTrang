package com.n3.project_thoitrang.controller.admin;

import com.n3.project_thoitrang.model.entity.Color;
import com.n3.project_thoitrang.model.entity.Size;
import com.n3.project_thoitrang.service.IColorService;
import com.n3.project_thoitrang.service.ISizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class SizeController {
    @Autowired
    private final ISizeService sizeService;
    @GetMapping("/size")
    public String color(Model model){
        model.addAttribute("size", sizeService.findAll());
        return "admin/size";
    }
    @GetMapping("/add-size")
    public String doAddSize(Model model){

        model.addAttribute("size",new Size());
        return "general/add-size";
    }
    @PostMapping("/addSize")
    public String addColor(@Valid @ModelAttribute("size") Size size, Model model, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("size",size);
            return "general/add-size";
        }else {
            Boolean bl=sizeService.save(size);
            if (bl){
                model.addAttribute("size", sizeService.findAll());
                return "admin/size";
            }else {
                model.addAttribute("size",size);
                model.addAttribute("error","inset is false");
                return "general/add-size";
            }
        }

    }
}
