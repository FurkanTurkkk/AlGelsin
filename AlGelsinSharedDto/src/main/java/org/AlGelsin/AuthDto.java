package org.AlGelsin;

import java.time.LocalDateTime;

public class AuthDto {

    private LocalDateTime lastLoginDate;
    private LocalDateTime registrationDate;

    public AuthDto() {
    }

    public AuthDto(LocalDateTime lastLoginDate, LocalDateTime registrationDate) {
        this.lastLoginDate = lastLoginDate;
        this.registrationDate = registrationDate;
    }


    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
