package com.n3.project_thoitrang.repository.impl;


import com.n3.project_thoitrang.dto.FormLogin;
import com.n3.project_thoitrang.model.entity.User;
import com.n3.project_thoitrang.repository.ILoginRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoginRegisterRepositoryImpl implements ILoginRegisterRepository {
    private final SessionFactory sessionFactory;

    @Override
    public User handleLogin(FormLogin formLogin) {
        Session session = sessionFactory.openSession();
        try {
            User user = session.createQuery("select u from User u where u.username like :username", User.class)
                    .setParameter("username", formLogin.getUserName())
                    .getSingleResult();
            if (user == null) {
                return null;
            }
            if (BCrypt.checkpw(formLogin.getPassword(), user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void handleRegister(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
