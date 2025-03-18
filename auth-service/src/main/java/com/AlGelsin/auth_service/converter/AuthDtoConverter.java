package com.AlGelsin.auth_service.converter;

import com.AlGelsin.auth_service.model.Auth;
import org.AlGelsin.AuthDto;
import org.springframework.stereotype.Component;

@Component
public class AuthDtoConverter {

    public AuthDto convert(Auth auth){
        return new AuthDto(
                auth.getLastLoginDate(),
                auth.getRegistrationDate()
        );
    }
}
