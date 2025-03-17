package com.AlGelsin.auth_service.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String username;
    private String password;
    private LocalDateTime registrationDate;
    private LocalDateTime LastLoginDate;
    private boolean isAdmin = false;


    public Auth(){}

    public Auth(String name,String surname,String username,String password){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return  name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public LocalDateTime getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        LastLoginDate = lastLoginDate;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}

