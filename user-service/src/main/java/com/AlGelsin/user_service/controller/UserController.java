package com.AlGelsin.user_service.controller;

import com.AlGelsin.user_service.dto.CreateUserRequestDto;
import com.AlGelsin.user_service.dto.UpdateUserRequestDto;
import com.AlGelsin.user_service.service.UserService;
import org.AlGelsin.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequestDto request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(
            @RequestHeader(value = "Auth-Id", required = true) Long authId,
            @RequestBody UpdateUserRequestDto request) {
        userService.updateUser(authId,request);

        System.out.println("🔥 Backend'e Gelen Auth-Id: " + authId); // Debug için

        return ResponseEntity.ok("✅ User updated successfully with Auth-Id: " + authId);
    }
}
