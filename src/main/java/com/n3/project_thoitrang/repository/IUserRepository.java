package com.n3.project_thoitrang.repository;

import com.n3.project_thoitrang.model.entity.User;

public interface IUserRepository {
    User findUsersByUserName(String userName);
    User save(User user);
}
