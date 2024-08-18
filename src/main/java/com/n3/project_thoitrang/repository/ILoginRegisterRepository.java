package com.n3.project_thoitrang.repository;

import com.n3.project_thoitrang.dto.FormLogin;
import com.n3.project_thoitrang.model.entity.User;

public interface ILoginRegisterRepository {
    User  handleLogin(FormLogin formLogin);

    void handleRegister(User user);
}
