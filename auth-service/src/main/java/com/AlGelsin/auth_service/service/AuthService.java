package com.AlGelsin.auth_service.service;

import com.AlGelsin.auth_service.converter.AuthDtoConverter;
import com.AlGelsin.auth_service.dto.CreateUserRequestDto;
import com.AlGelsin.auth_service.dto.LoginRequestDto;
import com.AlGelsin.auth_service.dto.RegisterRequestDto;
import com.AlGelsin.auth_service.exception.PasswordMismatchException;
import com.AlGelsin.auth_service.exception.UserAlreadyExistByUsername;
import com.AlGelsin.auth_service.feignclient.UserFeignClient;
import com.AlGelsin.auth_service.model.Auth;
import com.AlGelsin.auth_service.repository.AuthRepository;
import com.AlGelsin.auth_service.util.JwtUtil;
import org.AlGelsin.AuthDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserFeignClient userFeignClient;
    private final AuthDtoConverter converter;

    public AuthService(AuthRepository authRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil,
                       UserFeignClient userFeignClient, AuthDtoConverter converter) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userFeignClient = userFeignClient;
        this.converter = converter;
    }

    @Transactional
    public String register(RegisterRequestDto request) {
        if (request.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null.");
        }

        if(!request.getPassword().equals(request.getRePassword())){
            throw new PasswordMismatchException("Passwords must be same");
        }

        checkUsernameForRegistration(request.getUsername());

        Auth auth = new Auth(
                request.getName(),
                request.getSurname(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword())
        );
        auth.setRegistrationDate(LocalDateTime.now());
        authRepository.save(auth);

        userFeignClient.createUser(new CreateUserRequestDto(
                auth.getId(),
                request.getName(),
                request.getSurname(),
                request.getEmail()
        ));
        return "Kullanıcı kaydedildi.";
    }

    public String login(LoginRequestDto request) {
        Auth auth = authRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı."));

        if (passwordEncoder.matches(request.getPassword(), auth.getPassword())) {
            auth.setLastLoginDate(LocalDateTime.now());
            authRepository.save(auth);
            return jwtUtil.generateToken(request.getUsername(), auth.getId());
        } else {
            throw new PasswordMismatchException("Geçersiz şifre.");
        }
    }

    private void checkUsernameForRegistration(String username){
        Optional<Auth> auth = authRepository.findByUsername(username);
        if(auth.isPresent()){
            throw new UserAlreadyExistByUsername("Username already exist : "+username);
        }
    }

    public AuthDto getInformationById(Long authId) {
        Optional<Auth> auth = authRepository.findById(authId);
        if(auth.isEmpty()){
            throw new RuntimeException("Not found");
        }
        return converter.convert(auth.get());
    }
}
