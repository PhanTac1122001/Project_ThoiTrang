package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.dto.UserDto;
import com.n3.project_thoitrang.model.entity.User;

public interface IUserService {
    User findUsersByUserName(String userName);
    void saveUser(UserDto userDto);
    void saveAdmin(UserDto userDto);
}
