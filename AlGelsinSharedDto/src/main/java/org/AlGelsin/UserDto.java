package org.AlGelsin;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDto {

    private String id;
    private Long authId;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String tc;
    private LocalDate birthday;

    public UserDto() {
    }

    public UserDto(String id, Long authId,
                   String name,
                   String surname,
                   String phone,
                   String email,
                   String tc,
                   LocalDate birthday) {
        this.id = id;
        this.authId = authId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.tc = tc;
        this.birthday = birthday;
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

    public String getTc() {
        return tc;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
