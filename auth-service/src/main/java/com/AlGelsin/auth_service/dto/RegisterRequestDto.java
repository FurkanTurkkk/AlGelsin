package com.AlGelsin.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequestDto {

    @NotBlank(message = "Name can not blank")
    private String name;
    @NotBlank(message = "Surname can not blank")
    private String surname;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email can not blank")
    private String email;
    @NotBlank(message = "Username can not blank")
    private String username;
    @NotBlank(message = "Password can not blank")
    private String password;
    private String rePassword;

    public RegisterRequestDto() {
    }

    public RegisterRequestDto(String name,
                              String surname,
                              String email,
                              String username,
                              String password,
                              String rePassword) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.rePassword = rePassword;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail(){
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRePassword() {
        return rePassword;
    }
}
