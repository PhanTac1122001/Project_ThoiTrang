package com.n3.project_thoitrang.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ConfirmController {
    @RequestMapping("confirm")
    public String confirm(){
        return "user/confirm";
    }
}
