package com.n3.project_thoitrang.repository.impl;

import com.n3.project_thoitrang.model.entity.Category;
import com.n3.project_thoitrang.model.entity.Product_Detail;
import com.n3.project_thoitrang.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor

public class CategoryRepositoryImpl implements ICategoryRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.openSession();
        try {
            // HQL - Hibernate Query Language
            return session.createQuery("select a from Category as a order by id", Category.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Category findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            // HQL - Hibernate Query Language
            return session.get(Category.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public boolean save(Category category) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (category.getCategoryId() == null) {
                session.save(category);
            } else {
                session.update(category);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(findById(id));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Product_Detail> showAllProductDetails(Long categoryId) {
        Session session = sessionFactory.openSession();

        try {
            return session.createQuery("select pdt from Product_Detail as pdt where pdt.product.category.id = :categoryId",Product_Detail.class)
                    .setParameter("categoryId", categoryId)
                    .getResultList();

        } catch (Exception e) {

            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
