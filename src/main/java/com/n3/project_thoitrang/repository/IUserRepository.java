package com.n3.project_thoitrang.repository;

import com.n3.project_thoitrang.model.entity.User;

import java.util.List;

public interface IUserRepository {
    User findUsersByUserName(String userName);
    User save(User user);

    User findUserById(Long id);

    //phân trang, search
    List<User> findAllUser(int page, int size, String search);

    List<User> findAll();

    Long totalAllUser(String search);

    // Sắp xếp theo username tăng dần
    List<User> findAllByOrderByUsernameAsc(int page,int size);

    // Sắp xếp theo username giảm dần
    List<User> findAllByOrderByUsernameDesc(int page,int size);
}
