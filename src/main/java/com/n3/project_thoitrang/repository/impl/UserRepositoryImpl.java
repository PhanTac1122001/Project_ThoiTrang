package com.n3.project_thoitrang.repository.impl;

import com.n3.project_thoitrang.model.entity.Shoping_Cart;
import com.n3.project_thoitrang.model.entity.User;
import com.n3.project_thoitrang.repository.IUserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUsersByUserName(String userName) {
        Session session = sessionFactory.openSession();
        try {
            User user = (User) session.createQuery("from User where username = :userName")
                    .setParameter("userName", userName)
                    .getSingleResult();
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public User save(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            if (user.getUserId() == null) {
                session.save(user);
            } else {
                session.update(user);
            }
            session.getTransaction().commit();
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }


    @Override
    public List<User> findAllUser(int page, int size, String search) {
        Session session = sessionFactory.openSession();
        try {
            String hql = "from User";
            if (!search.isEmpty()) {
                hql += " u where u.username like concat('%',:search,'%')";
            }
            List<User> user;
            if (search.isEmpty()) {
                user = session.createQuery(hql, User.class)
                        .setFirstResult(page * size)
                        .setMaxResults(size)
                        .getResultList();
            } else {
                user = session.createQuery(hql, User.class)
                        .setParameter("search", search)
                        .setFirstResult(page * size)
                        .setMaxResults(size)
                        .getResultList();
            }
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> findAll() {
        Session session=sessionFactory.openSession();
        try {
            return session.createQuery("select u from User as u", User.class).getResultList();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public Long totalAllUser(String search) {
        Session session = sessionFactory.openSession();
        try {
            if (search.isEmpty()) {
                return session.createQuery("select count(u) from User u", Long.class)
                        .getSingleResult();
            } else {
                return session.createQuery("select count(u) from User u where u.username like concat('%',:search,'%') ", Long.class)
                        .setParameter("search", search)
                        .getSingleResult();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> findAllByOrderByUsernameAsc(int page,int size) {
        Session session = sessionFactory.openSession();
        try {

            return session.createQuery("select u from User u order by u.username desc ", User.class)
                    .setFirstResult(page * size)
                    .setMaxResults(size)
                    .getResultList();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }


    @Override
    public List<User> findAllByOrderByUsernameDesc(int page,int size) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("select u from User u order by u.username asc ", User.class)
                    .setFirstResult(page * size)
                    .setMaxResults(size)
                    .getResultList();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }


    @Override
    public User findUserById(Long id) {
        Session session = sessionFactory.openSession();
        try {
//            return (User) session.createQuery("from User where id = :id")
//                    .setParameter("id",id)
//                    .getSingleResult();
            return session.get(User.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
