package com.n3.project_thoitrang.repository;

import com.n3.project_thoitrang.model.entity.Category;
import com.n3.project_thoitrang.model.entity.Shoping_Cart;

import java.util.List;

public interface ICartRepository {
    List<Shoping_Cart> findAll();


    void deleteById(Long id);

    boolean save(Shoping_Cart shopingCart);

    Shoping_Cart findById(Long id);

    Shoping_Cart findByUserIdAndProductId(Long userId,Long productId);

    List<Shoping_Cart> findByUserId(Long userId);
}
