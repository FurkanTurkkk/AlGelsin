package com.AlGelsin.user_service.converter;

import com.AlGelsin.user_service.model.User;
import org.AlGelsin.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto convert(User user){
        return new UserDto(
                user.getId(),
                user.getAuthId(),
                user.getName(),
                user.getSurname(),
                user.getPhone(),
                user.getEmail(),
                user.getBirthday()
        );
    }
}
