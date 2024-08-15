package com.n3.project_thoitrang.repository;

import com.n3.project_thoitrang.model.entity.User;

import java.util.List;

public interface IUserRepository {
    User findUsersByUserName(String userName);
    User save(User user);
    //phân trang, search
    List<User> findAllUser(int page, int size, String search);

    Long totalAllUser(String search);

    // Sắp xếp theo username tăng dần
    List<User> findAllByOrderByUsernameAsc();

    // Sắp xếp theo username giảm dần
    List<User> findAllByOrderByUsernameDesc();
}
