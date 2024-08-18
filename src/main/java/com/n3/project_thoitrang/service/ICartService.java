package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.model.entity.Image;
import com.n3.project_thoitrang.model.entity.Shoping_Cart;

import java.util.List;
import java.util.Map;

public interface ICartService {
    List<Shoping_Cart> findAll();

    void deleteById(Long id);

   boolean save(Shoping_Cart shopingCart);

    Shoping_Cart findById(Long id);
    List<Shoping_Cart> getCartItemsByUserId(Long userId);
    void addProductToCart(Long userId, Long productDetailId, Integer quantity);

    List<Image> getAllImagesByProductDetailId(Long productDetailId);
    Map<Long, Image> getSingleImageForEachProduct(List<Shoping_Cart> shopingCarts);
}
