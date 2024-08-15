package com.n3.project_thoitrang.repository.impl;

import com.n3.project_thoitrang.model.entity.Shoping_Cart;
import com.n3.project_thoitrang.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements ICartRepository {
    private final SessionFactory sessionFactory;
    @Override
    public List<Shoping_Cart> findAll() {
        Session session=sessionFactory.openSession();
        try {
            return session.createQuery("select c from Shoping_Cart as c",Shoping_Cart.class).getResultList();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }

    }

    @Override
    public void deleteById(Integer id) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.delete(findById(id));
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public void updateCart(Shoping_Cart shopingCart) {
        Session session =sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        try {
            if (shopingCart.getShoppingCartId()==null){
                session.save(shopingCart);
            }
            else {
                session.update(shopingCart);
            }
            transaction.commit();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public Shoping_Cart findById(Integer id) {
        Session session=sessionFactory.openSession();
        try {
            return session.get(Shoping_Cart.class,id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }


}
