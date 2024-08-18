package com.n3.project_thoitrang.repository.impl;

import com.n3.project_thoitrang.model.entity.Banner;
import com.n3.project_thoitrang.model.entity.Category;
import com.n3.project_thoitrang.repository.IBannerRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
@RequiredArgsConstructor

public class IBannerRepositoryImpl implements IBannerRepository {
    private final SessionFactory sessionFactory;

    @Override
    public List<Banner> findAll() {
        Session session = sessionFactory.openSession();
        try
        {
            // HQL - Hibernate Query Language
            return session.createQuery("select b from Banner as b order by id", Banner.class)
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
    public boolean save(Banner banner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try
        {
            if (banner.getId() == null)
            {
                session.save(banner);
            }
            else
            {
                session.update(banner);
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
    public Banner findById(Long id) {
        Session session = sessionFactory.openSession();
        try
        {
            // HQL - Hibernate Query Language
            return session.get(Banner.class, id);
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
