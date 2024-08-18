package com.n3.project_thoitrang.controller.admin;

import com.n3.project_thoitrang.model.entity.Banner;
import com.n3.project_thoitrang.model.entity.Color;
import com.n3.project_thoitrang.service.IBannerService;
import com.n3.project_thoitrang.service.IColorService;
import com.n3.project_thoitrang.service.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class BannerController {
    @Autowired
    private final IBannerService bannerService;
    private final UploadFile uploadFile;
    @GetMapping("/banner")
    public String color(Model model){
        model.addAttribute("banner", bannerService.findAll());
        return "admin/banner";
    }
    @GetMapping("/add-banner")
    public String doAddColor(Model model){

        model.addAttribute("banner",new Banner());
        return "general/add-banner";
    }
    @PostMapping("/addBanner")
    public String addColor(@Valid @ModelAttribute("banner") Banner banner, Model model, BindingResult result ,@RequestParam("bannerFile") MultipartFile file){
        if (result.hasErrors()){
            model.addAttribute("banner",banner);
            return "general/add-banner";
        }else {
            Boolean bl=bannerService.save(banner);
            if (bl){
                String urlImage= uploadFile.uploadLocal(file);
                banner.setImage(urlImage);
                bannerService.save(banner);
                model.addAttribute("banner", bannerService.findAll());
                return "admin/color";
            }else {
                model.addAttribute("banner",banner);
                model.addAttribute("error","inset is false");
                return "general/add-banner";
            }
        }

    }
}
