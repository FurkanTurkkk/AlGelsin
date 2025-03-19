package com.AlGelsin.user_service.dto;

import java.time.LocalDate;

public class UpdateUserRequestDto {
    private String phone;
    private LocalDate birthday;
    private String tc;

    public UpdateUserRequestDto(){

    }

    public UpdateUserRequestDto(String phone, LocalDate birthday,String tc) {
        this.phone = phone;
        this.birthday = birthday;
        this.tc = tc;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getTc() {
        return tc;
    }
}
