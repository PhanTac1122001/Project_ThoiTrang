package com.n3.project_thoitrang.repository.impl;

import com.n3.project_thoitrang.model.entity.Color;
import com.n3.project_thoitrang.model.entity.Size;
import com.n3.project_thoitrang.repository.ISizeRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class SizeRepositoryImpl implements ISizeRepository {
    private final SessionFactory sessionFactory;

    @Override
    public List<Size> findAll() {
        Session session=sessionFactory.openSession();
        try {
            return session.createQuery("select s from Size as s order by id",Size.class).getResultList();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public boolean save(Size size) {
        Session session=sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (size.getSizeId()==null){
                session.save(size);
            }
            else {
                session.update(size);
            }
            transaction.commit();
            return true;
        }   catch (Exception e){
            throw new RuntimeException(e);

        }
        finally {
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
    public Size findById(Long id) {
        Session session = sessionFactory.openSession();
        try
        {
            // HQL - Hibernate Query Language
            return session.get(Size.class, id);
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
