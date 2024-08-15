package com.n3.project_thoitrang.repository.impl;

import com.n3.project_thoitrang.model.entity.Image;
import com.n3.project_thoitrang.model.entity.Shoping_Cart;
import com.n3.project_thoitrang.repository.IImageRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl implements IImageRepository {
    private final SessionFactory sessionFactory;

    @Override
    public List<Image> findAll() {
        Session session=sessionFactory.openSession();
        try {
            return session.createQuery("select i from Image as i", Image.class).getResultList();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public Image findById(Long id) {
        Session session=sessionFactory.openSession();
        try {
            return session.get(Image.class,id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public boolean save(Image image) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try
        {
            if (image.getId() == null)
            {
                session.save(image);
            }
            else
            {
                session.update(image);
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
}
