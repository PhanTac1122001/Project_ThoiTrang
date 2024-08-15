package com.n3.project_thoitrang.controller;

import com.n3.project_thoitrang.model.entity.Image;
import com.n3.project_thoitrang.model.entity.Product;
import com.n3.project_thoitrang.model.entity.Shoping_Cart;
import com.n3.project_thoitrang.model.entity.User;
import com.n3.project_thoitrang.service.ICartService;
import com.n3.project_thoitrang.service.ICategoryService;
import com.n3.project_thoitrang.service.IProductService;
import com.n3.project_thoitrang.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;
    private final IUserService userService;
    private final ICategoryService categoryService;
    private  final IProductService productService;
    public String cart(){
        return "user/cart";
    }

    @GetMapping("cart")
    public String findAll(Model model){

        model.addAttribute("shop",cartService.findAll());
        model.addAttribute("user", userService.findAll());
        model.addAttribute("products", productService.findAll());
        return "user/cart";
    }
    @GetMapping("/deletecart/{id}")
    public String deleteCart(@PathVariable Long id)
    {
        cartService.deleteById(id);
        return "user/cart";
    }
    //shopping-cart/{userid}
    @GetMapping("/shopping-cart/{id}")
    public String shoppingCart(@PathVariable("id") Long userId, Model model) {
        List<Shoping_Cart> cartItemList = cartService.getCartItemsByUserId(userId);

        // Get a map of ProductDetail ID to a single Image
        Map<Long, Image> productImagesMap = cartService.getSingleImageForEachProduct(cartItemList);

        model.addAttribute("cartItems", cartItemList);
        model.addAttribute("productImagesMap", productImagesMap);

//        for ( cart : cartItemLis)
        return "user/cart";
    }

    @PostMapping("/user/shopping-cart/{id}")
    public String shoppingCart(@PathVariable("id") Long productId, @RequestParam("quantity") Integer quantity, Model model) {
        // Assuming you have the user ID from the session or security context
//        Long userId = getCurrentUserId();

        // Add the product to the cart
//        cartService.addProductToCart(userId, productId, quantity);

        // Redirect to the shopping cart view or another page
//        model.addAttribute("banners",bannerService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        return "user/index"; // Adjust the redirect as needed
    }

}
