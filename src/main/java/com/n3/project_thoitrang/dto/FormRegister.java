package com.n3.project_thoitrang.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FormRegister {


    @NotBlank(message = "Email is empty!")
    @Email(message = "Email not valid")
    private String email;

    @NotBlank(message = "username is empty!")
    private String username;

    @NotEmpty(message = "password must me not empty!")
    private String password;

    @NotBlank(message = "Phone is empty!")
    private String phone;

    private String confirmPassword;
    private String address;
    private String fullName;

//    @NotBlank(message = "username is empty!")

}
