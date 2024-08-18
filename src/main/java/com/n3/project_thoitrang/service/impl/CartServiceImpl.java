package com.n3.project_thoitrang.service.impl;

import com.n3.project_thoitrang.model.entity.Image;
import com.n3.project_thoitrang.model.entity.Shoping_Cart;
import com.n3.project_thoitrang.repository.ICartRepository;
import com.n3.project_thoitrang.service.ICartService;
import com.n3.project_thoitrang.service.IImageService;
import com.n3.project_thoitrang.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

//    @Autowired
//    private IProductDetailService productDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IImageService imageService;

    @Override
    public List<Shoping_Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public boolean save(Shoping_Cart shopingCart) {
        return cartRepository.save(shopingCart);
    }


    @Override
    public Shoping_Cart findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public List<Shoping_Cart> getCartItemsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public void addProductToCart(Long userId, Long productDetailId, Integer quantity) {
        Shoping_Cart existingCart = cartRepository.findByUserIdAndProductId(userId, productDetailId);
        if (existingCart != null) {
            // If the item is already in the cart, just update the quantity
            existingCart.setOrderQuantity(existingCart.getOrderQuantity() + quantity);
            cartRepository.save(existingCart);
        } else {
            // If the item is not in the cart, create a new CartItem
            Shoping_Cart newCartItem = new Shoping_Cart();
            newCartItem.setUser(userService.findUserById(userId));
//            newCartItem.setProduct(productDetailService.findById(productId));
            newCartItem.setOrderQuantity(quantity);
            cartRepository.save(newCartItem);
        }
    }

    @Override
    public List<Image> getAllImagesByProductDetailId(Long productDetailId) {
        return imageService.findAll().stream()
                .filter(image -> image.getProductDetail().getProductDetailId().equals(productDetailId))
                .collect(Collectors.toList());
    }




    @Override
    public Map<Long, Image> getSingleImageForEachProduct(List<Shoping_Cart> shopingCarts) {
        return shopingCarts.stream()
                .collect(Collectors.toMap(
                        shopingCart -> shopingCart.getProductDetail().getProductDetailId(),
                        shopingCart -> imageService.findAll().stream()
                                .filter(image -> image.getProductDetail().getProductDetailId().equals(shopingCart.getProductDetail().getProductDetailId()))
                                .findFirst()
                                .orElse(null)
                ));
    }


}
