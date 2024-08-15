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

    @NotBlank(message = "confirmPassword is empty!")
    private String confirmPassword;

    @NotBlank(message = "address is empty")
    private String address;

    @NotBlank(message = "full name is empty")
    private String fullName;

    private String avatar;

//    @NotBlank(message = "username is empty!")

}
