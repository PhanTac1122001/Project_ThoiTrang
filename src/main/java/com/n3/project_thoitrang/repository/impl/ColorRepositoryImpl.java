package com.n3.project_thoitrang.repository.impl;

import com.n3.project_thoitrang.model.entity.Banner;
import com.n3.project_thoitrang.model.entity.Color;
import com.n3.project_thoitrang.repository.IColorRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class ColorRepositoryImpl implements IColorRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<Color> findAll() {
        Session session = sessionFactory.openSession();
        try
        {
            // HQL - Hibernate Query Language
            return session.createQuery("select c from Color as c order by id", Color.class)
                    .getResultList();
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

    @Override
    public boolean save(Color color) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try
        {
            if (color.getColorId() == null)
            {
                session.save(color);
            }
            else
            {
                session.update(color);
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
    public Color findById(Long id) {
        Session session = sessionFactory.openSession();
        try
        {
            // HQL - Hibernate Query Language
            return session.get(Color.class, id);
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
