package com.n3.project_thoitrang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("login")
    public String login() {return "/login/login";}



    @RequestMapping("register")
    public String register() {return "/login/register";}






}
