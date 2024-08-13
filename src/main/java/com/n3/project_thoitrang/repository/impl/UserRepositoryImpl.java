package com.n3.project_thoitrang.repository.impl;

import com.n3.project_thoitrang.model.entity.User;
import com.n3.project_thoitrang.repository.IUserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements IUserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUsersByUserName(String userName) {
        Session session = sessionFactory.openSession();
        try{
            User user = (User) session.createQuery("from User where username = :userName")
                    .setParameter("userName",userName)
                    .getSingleResult();
            return user;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public User save(User user) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return user;
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return null;
    }

}
