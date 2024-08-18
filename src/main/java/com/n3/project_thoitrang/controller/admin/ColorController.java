package com.n3.project_thoitrang.controller.admin;

import com.n3.project_thoitrang.model.entity.Color;
import com.n3.project_thoitrang.service.IColorService;
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
public class ColorController {
    @Autowired
    private final IColorService colorService;
    @GetMapping("/color")
    public String color(Model model){
        model.addAttribute("color", colorService.findAll());
        return "admin/color";
    }
    @GetMapping("/add-color")
    public String doAddColor(Model model){

        model.addAttribute("color",new Color());
        return "general/add-color";
    }
    @PostMapping("/addColor")
    public String addColor(@Valid @ModelAttribute("color") Color color, Model model, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("color",color);
            return "general/add-color";
        }else {
            Boolean bl=colorService.save(color);
            if (bl){
                model.addAttribute("color", colorService.findAll());
                return "admin/color";
            }else {
                model.addAttribute("color",color);
                model.addAttribute("error","inset is false");
                return "general/add-color";
            }
        }

    }
}
