package com.n3.project_thoitrang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @RequestMapping(value = {"/","index"})
    public String index(){return "index";}

}
