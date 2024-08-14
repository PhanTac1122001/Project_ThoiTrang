package com.n3.project_thoitrang.service.impl;

import com.n3.project_thoitrang.model.entity.Shoping_Cart;
import com.n3.project_thoitrang.repository.ICartRepository;
import com.n3.project_thoitrang.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
    private final ICartRepository cartRepository;

    @Override
    public List<Shoping_Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
    cartRepository.deleteById(id);
    }

    @Override
    public void updateCart(Shoping_Cart shopingCart) {
cartRepository.updateCart(shopingCart);
    }

    @Override
    public Shoping_Cart findById(Integer id) {
        return cartRepository.findById(id);
    }
}
