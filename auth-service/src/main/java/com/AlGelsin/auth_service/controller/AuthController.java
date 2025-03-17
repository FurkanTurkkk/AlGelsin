package com.AlGelsin.auth_service.controller;

import com.AlGelsin.auth_service.dto.LoginRequestDto;
import com.AlGelsin.auth_service.dto.RegisterRequestDto;
import com.AlGelsin.auth_service.service.AuthService;
import jakarta.validation.Valid;
import org.AlGelsin.AuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDto request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request){
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping
    public ResponseEntity<AuthDto> getInformationById(@RequestHeader("Auth-Id")Long authId){
        return ResponseEntity.ok(authService.getInformationById(authId));
    }
}
