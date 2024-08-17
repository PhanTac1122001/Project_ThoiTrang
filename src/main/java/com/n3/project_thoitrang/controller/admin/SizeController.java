package com.n3.project_thoitrang.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/size")
public class SizeController {
    @GetMapping()
    public String size(){
        return "admin/size";
    }
}
