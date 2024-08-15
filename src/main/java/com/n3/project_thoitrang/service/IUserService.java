package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.dto.UserDto;
import com.n3.project_thoitrang.model.entity.User;

import java.util.List;

public interface IUserService {
    User findUsersByUserName(String userName);
    void saveUser(UserDto userDto);
    void saveAdmin(UserDto userDto);

    List<User> findAllUser(int page, int size, String search);

    Long totalAllUser(String search);

    // Sắp xếp theo username tăng dần
    List<User> findAllByOrderByUsernameAsc();

    // Sắp xếp theo username giảm dần
    List<User> findAllByOrderByUsernameDesc();
}
