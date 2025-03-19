package com.AlGelsin.user_service.service;

import com.AlGelsin.user_service.converter.UserDtoConverter;
import com.AlGelsin.user_service.dto.CreateUserRequestDto;
import com.AlGelsin.user_service.dto.UpdateUserRequestDto;
import com.AlGelsin.user_service.exception.EmailAddressAlreadyExist;
import com.AlGelsin.user_service.exception.UserNotFoundByAuthIdException;
import com.AlGelsin.user_service.model.User;
import com.AlGelsin.user_service.repository.UserRepository;
import org.AlGelsin.UserDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter converter;

    public UserService(UserRepository userRepository, UserDtoConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public UserDto createUser(CreateUserRequestDto request) {
        checkByEmailForCreateUser(request.getEmail());
        User user = userRepository.save(new User(
                request.getAuthId(),
                request.getName(),
                request.getSurname(),
                request.getEmail()
        ));
        return converter.convert(user);
    }

    public UserDto updateUser(Long authId, UpdateUserRequestDto request) {
        User user = checkByAuthIdForUpdateUser(authId);
        user.setBirthday(request.getBirthday());
        user.setPhone(request.getPhone());
        user.setTc(request.getTc());
        userRepository.save(user);
        return converter.convert(user);
    }

    private void checkByEmailForCreateUser(String email) {
        Optional<User> userDetails = userRepository.findByEmail(email);
        if(userDetails.isPresent()){
            throw new EmailAddressAlreadyExist("This email address already exist...");
        }
    }

    private User checkByAuthIdForUpdateUser(Long authId){
        return userRepository.findByAuthId(authId)
                .orElseThrow(()->new UserNotFoundByAuthIdException("User can not found by auth id "));
    }

    public String getUserIdByAuthId(Long authId) {
        Optional<User> user = userRepository.findByAuthId(authId);
        return user.get().getId();
    }

    public UserDto getUserByAuthId(Long authId) {
        Optional<User> user = userRepository.findByAuthId(authId);
        return converter.convert(user.get());
    }

}
