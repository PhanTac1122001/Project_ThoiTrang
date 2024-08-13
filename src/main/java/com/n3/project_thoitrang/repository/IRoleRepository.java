package com.n3.project_thoitrang.repository;


import com.n3.project_thoitrang.model.entity.Role;

public interface IRoleRepository {
    Role findRolesByRoleName(String roleName);
}
