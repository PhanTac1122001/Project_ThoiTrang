package com.n3.project_thoitrang.repository.impl;

import com.n3.project_thoitrang.model.entity.Role;
import com.n3.project_thoitrang.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements IRoleRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Role findRolesByRoleName(Role.RoleName roleName) {
        Session session = sessionFactory.openSession();
        try{
            Role role = (Role) session.createQuery("from Role where roleName = :roleName")
                    .setParameter("roleName",roleName)
                    .getSingleResult();
            return role;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
