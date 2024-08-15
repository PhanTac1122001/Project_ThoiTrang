package com.n3.project_thoitrang.service;

import com.n3.project_thoitrang.dto.FormLogin;
import com.n3.project_thoitrang.model.entity.User;

public interface ILoginRigisterService {
    User handleLogin(FormLogin formLogin);

//    void handleRegister(FormRegister formRegister);

    void handleLogout();
}
