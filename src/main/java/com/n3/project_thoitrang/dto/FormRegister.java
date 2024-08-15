package com.n3.project_thoitrang.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class FormRegister {
    @NotBlank(message = "Username is empty!")
    @Email(message = "Email not valid")
    private String email;

    @NotEmpty(message = "phone must be not empty")
//    @DataExist(message = "phone is already exist", entityClass = Users.class, existName = "phone")
    private String phone;

    @NotEmpty(message = "password must me not empty")
    private String password;
}
