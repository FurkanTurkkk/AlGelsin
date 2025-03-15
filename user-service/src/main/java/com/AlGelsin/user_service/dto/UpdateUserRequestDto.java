package com.AlGelsin.user_service.dto;

import java.time.LocalDate;

public class UpdateUserRequestDto {
    private String phone;
    private LocalDate birthday;

    public UpdateUserRequestDto(){

    }

    public UpdateUserRequestDto(String phone, LocalDate birthday) {
        this.phone = phone;
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
