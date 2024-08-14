package com.n3.project_thoitrang.controller;

import com.n3.project_thoitrang.model.entity.Product;
import com.n3.project_thoitrang.model.entity.User;
import com.n3.project_thoitrang.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;
    public String cart(){
        return "user/cart";
    }

    @GetMapping("/cart")
    public String findAll(Model model){

        model.addAttribute("shop",cartService.findAll());
        return "user/cart";
    }
//    @GetMapping("/delete/{id}")
//    public String deleteCart(@PathVariable Integer id){
//    cartService.deleteById(id);
//    return "redirect:/";
//    }
}
