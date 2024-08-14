package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.model.entity.Shoping_Cart;

import java.util.List;

public interface ICartService {
    List<Shoping_Cart> findAll();

    void deleteById(Integer id);

    void updateCart(Shoping_Cart shopingCart);

    Shoping_Cart findById(Integer id);


}
