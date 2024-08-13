package com.n3.project_thoitrang.controller;

import com.n3.project_thoitrang.dto.UserDto;
import com.n3.project_thoitrang.model.entity.User;
import com.n3.project_thoitrang.service.IUserService;
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
public class WebController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = {"index","/"})
    public String index(){return "index";}

    @RequestMapping(value = {"cart","/"})
    public String cart(){return "user/cart";}


    @GetMapping(value = {"/","/index"})
    public String home(){
        return "index";
    }

    @GetMapping("/register/user")
    public String registerUser(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user",user);
        return "register_user";
    }

    @GetMapping("/register/admin")
    public String registerAdmin(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user",user);
        return "register_admin";
    }

    @PostMapping("/register/saveUser")
    public String saveUser(@Valid @ModelAttribute UserDto userDto, BindingResult result, Model model){
        User user = userService.findUsersByUserName(userDto.getUserName());
        if(user!=null){
            result.rejectValue("userName",null,"Username is already existed!");
            model.addAttribute("user",userDto);
            return "register_user";
        }
        if(result.hasErrors()){
            model.addAttribute("user",userDto);
            return "register_user";
        }

        userService.saveUser(userDto);
        model.addAttribute("success","Register user successfully!");
        model.addAttribute("user",userDto);
        return "login";
    }

    @PostMapping("/register/saveAdmin")
    public String saveAdmin(@Valid @ModelAttribute UserDto userDto, BindingResult result, Model model){
        User user = userService.findUsersByUserName(userDto.getUserName());
        if(user!=null){
            result.rejectValue("userName",null,"Username is already existed!");
            model.addAttribute("user",userDto);
            return "register_admin";
        }
        if(result.hasErrors()){
            model.addAttribute("user",userDto);
            return "register_admin";
        }

        userService.saveAdmin(userDto);
        model.addAttribute("success","Register admin successfully!");
        model.addAttribute("user",userDto);
        return "register_admin";
    }

    @RequestMapping ("/login")
    public String login(){
        return "login/login";
    }
}
