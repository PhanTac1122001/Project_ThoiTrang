package com.n3.project_thoitrang.controller;

import com.n3.project_thoitrang.model.entity.*;
import com.n3.project_thoitrang.service.*;
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
    private  final IProductDetailService productDetailService;
    private  final IProductService productService;
    private final IBannerService bannerService;

    public String cart(){
        return "user/cart";
    }

    @GetMapping("cart/{productDetailId}")
    public String findAll(Model model, @PathVariable("productDetailId") Long id){
        Product_Detail productDetail=productDetailService.findById(id);
        Shoping_Cart shopingCart=new Shoping_Cart();
        shopingCart.setProductDetail(productDetail);
        User user=userService.findUserById(1L);
        shopingCart.setUser(user);
        cartService.addProductToCart(user.getUserId(),productDetail.getProductDetailId(), 3);

        return "redirect:/list";
    }
    @GetMapping("/deleteCart/{id}")
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

        return "user/cart";
    }

    @PostMapping("/user/cart/{id}")
    public String shoppingCart(@PathVariable("id") Long productId, @RequestParam("quantity") Integer quantity, Model model) {
//         Assuming you have the user ID from the session or security context
        Long userId = getCurrentUserId();

//         Add the product to the cart
        cartService.addProductToCart(userId, productId, quantity);

//         Redirect to the shopping cart view or another page
        model.addAttribute("banners",bannerService.findAll());

        return "user/index"; // Adjust the redirect as needed
    }
    private Long getCurrentUserId() {

        return 1L; // Placeholder user ID
    }
}
