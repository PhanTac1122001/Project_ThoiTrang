package com.n3.project_thoitrang.repository.impl;

import com.n3.project_thoitrang.model.entity.Category;
import com.n3.project_thoitrang.model.entity.Product_Detail;
import com.n3.project_thoitrang.repository.IProductDetailRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDetailReopositoryImpl implements IProductDetailRepository {


    private final SessionFactory sessionFactory;

    @Override
    public List<Product_Detail> findAll() {
        Session session=sessionFactory.openSession();
        try {
            return session.createQuery("select pd from Product_Detail as pd order by id",Product_Detail.class).getResultList();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try
        {
            session.delete(findById(id));
            transaction.commit();
        }
        catch (Exception e)
        {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        finally
        {
            session.close();
        }
    }

    @Override
    public boolean save(Product_Detail product_detail) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try
        {
            if (product_detail.getProductDetailId() == null)
            {
                session.save(product_detail);
            }
            else
            {
                session.update(product_detail);
            }
            transaction.commit();
            return true;
        }
        catch (Exception e)
        {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        finally
        {
            session.close();
        }
    }

    @Override
    public Product_Detail findById(Long id) {
        Session session = sessionFactory.openSession();
        try
        {
            // HQL - Hibernate Query Language
            return session.get(Product_Detail.class, id);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            session.close();
        }
    }
}
