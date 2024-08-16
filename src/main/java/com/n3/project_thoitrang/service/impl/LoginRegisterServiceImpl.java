package com.n3.project_thoitrang.service.impl;

import com.n3.project_thoitrang.dto.FormLogin;
import com.n3.project_thoitrang.dto.FormRegister;
import com.n3.project_thoitrang.model.entity.Role;
import com.n3.project_thoitrang.model.entity.User;
import com.n3.project_thoitrang.repository.ILoginRegisterRepository;
import com.n3.project_thoitrang.repository.IRoleRepository;
import com.n3.project_thoitrang.service.ILoginRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LoginRegisterServiceImpl implements ILoginRegisterService {
private final HttpSession session;
private final IRoleRepository roleRepository;
private final ILoginRegisterRepository loginRegisterRepository;
    @Override
    public User handleLogin(FormLogin formLogin) {
        User user = loginRegisterRepository.handleLogin(formLogin);
        session.setAttribute("data_user", user);
        session.setAttribute("your_name", "<i class='fa-solid fa-crown'></i> " + user.getFullname());
        return user;
    }

    @Override
    public void handleRegisterUser(FormRegister formRegister) {
        List<Role> role = new ArrayList<>();
        role.add(roleRepository.findRolesByRoleName(Role.RoleName.USER));
        User user = User.builder()
                .username(formRegister.getUsername())
                .password(BCrypt.hashpw(formRegister.getPassword(), BCrypt.gensalt(12)))
                .role(role)
                .status(true)
                .email(formRegister.getEmail())
                .phone(formRegister.getPhone())
                .address(formRegister.getAddress())
                .fullname(formRegister.getFullName())
                .avatar(formRegister.getAvatar())
                .updatedAt(new Date())
                .createdAt(new Date())
                .build();
        loginRegisterRepository.handleRegister(user);
        //save role cho user nay


    }

    @Override
    public void handleRegisterAdmin(FormRegister formRegister) {
        List<Role> role = new ArrayList<>();
        role.add(roleRepository.findRolesByRoleName(Role.RoleName.ADMIN));
        User user = User.builder()
                .username(formRegister.getUsername())
                .password(BCrypt.hashpw(formRegister.getPassword(), BCrypt.gensalt(12)))
                .role(role)
                .status(true)
                .email(formRegister.getEmail())
                .phone(formRegister.getPhone())
                .address(formRegister.getAddress())
                .fullname(formRegister.getFullName())
                .avatar(formRegister.getAvatar())
                .updatedAt(new Date())
                .createdAt(new Date())
                .build();
        loginRegisterRepository.handleRegister(user);
        //save role cho user nay
    }

    @Override
    public void handleLogout() {
    session.invalidate();
    }
}
