package com.n3.project_thoitrang.service.impl;

import com.n3.project_thoitrang.dto.UserDto;
import com.n3.project_thoitrang.model.entity.Role;
import com.n3.project_thoitrang.model.entity.User;
import com.n3.project_thoitrang.repository.IRoleRepository;
import com.n3.project_thoitrang.repository.IUserRepository;
import com.n3.project_thoitrang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public User findUsersByUserName(String userName) {
        return userRepository.findUsersByUserName(userName);
    }

    @Override
    public void saveUser(UserDto userDto) {
        //Chuyen doi tu userDto ve doi tuong Users de save vao database
        User user = User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUserName())
                .address(userDto.getAddress())
                .fullname(userDto.getFullName())
                .build();
        //Gan quyen cho doi tuong users
        Role roleUser = roleRepository.findRolesByRoleName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);
        user.setRole(roles);
        //Ma hoa mat khau cua users
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
    }

    @Override
    public void saveAdmin(UserDto userDto) {
        //Chuyen doi tu userDto ve doi tuong Users de save vao database
        User user = User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUserName())
                .address(userDto.getAddress())
                .fullname(userDto.getFullName())
                .build();
        //Gan quyen cho doi tuong users
        Role roleUser = roleRepository.findRolesByRoleName("ROLE_USER");
        Role roleAdmin = roleRepository.findRolesByRoleName("ROLE_ADMIN");
        List<Role> role = new ArrayList<>();
        role.add(roleUser);
        role.add(roleAdmin);
        user.setRole(role);
        //Ma hoa mat khau cua users
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
    }

    @Override
    public List<User> findAllUser(int page, int size, String search) {
        return userRepository.findAllUser(page, size, search);
    }

    @Override
    public Long totalAllUser(String search) {
       return userRepository.totalAllUser(search);
    }

    //sắp xếp
    @Override
    public List<User> findAllByOrderByUsernameAsc() {
        return userRepository.findAllByOrderByUsernameAsc();
    }
    //sắp xếp
    @Override
    public List<User> findAllByOrderByUsernameDesc() {
        return userRepository.findAllByOrderByUsernameDesc();
    }
}