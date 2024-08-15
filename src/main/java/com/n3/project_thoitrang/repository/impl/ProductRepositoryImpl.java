package com.n3.project_thoitrang.repository.impl;

import com.n3.project_thoitrang.model.entity.Product;
import com.n3.project_thoitrang.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor

public class ProductRepositoryImpl implements IProductRepository {
    private final SessionFactory sessionFactory;
    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        try{
            List list = session.createQuery("from Product",Product.class).getResultList();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;



    }

    @Override
    public Product getProductById(Long proId) {
        Session session = sessionFactory.openSession();
        try{
            Product product = session.get(Product.class, proId);
            return product;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean save(Product pro) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.save(pro);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(Product pro) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.update(pro);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Long proId) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.delete(getProductById(proId));
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Product> findByName(String proName) {
        Session session = sessionFactory.openSession();
        try{
            if(proName==null || proName.isEmpty())
                proName = "%";
            else
                proName = "%"+proName+"%";
            List list = session.createQuery("from Product where productName like :proName")
                    .setParameter("proName",proName)
                    .list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
