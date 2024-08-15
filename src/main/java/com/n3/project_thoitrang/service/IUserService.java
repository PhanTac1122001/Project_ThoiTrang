package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.dto.FormLogin;
import com.n3.project_thoitrang.model.entity.User;

import java.util.List;

public interface IUserService {
    User findUsersByUserName(String userName);
    void saveUser(FormLogin userDto);
    void saveAdmin(FormLogin userDto);

    User save(User user);

    List<User> findAllUser(int page, int size, String search);

    Long totalAllUser(String search);

    // Sắp xếp theo username tăng dần
    List<User> findAllByOrderByUsernameAsc();

    // Sắp xếp theo username giảm dần
    List<User> findAllByOrderByUsernameDesc();

    User findUserById(Long id);
}
