package com.AlGelsin.user_service.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long authId;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private LocalDate birthday;
    private String addressId;

    public User(){

    }

    public User(Long authId, String name, String surname, String email) {
        this.authId = authId;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public Long getAuthId() {
        return authId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getAddressId(){
        return addressId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setAddressId(String addressId){
        this.addressId = addressId;
    }
}
