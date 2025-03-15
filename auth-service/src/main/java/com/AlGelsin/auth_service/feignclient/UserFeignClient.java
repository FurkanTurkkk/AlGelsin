package com.AlGelsin.auth_service.feignclient;

import com.AlGelsin.auth_service.dto.CreateUserRequestDto;
import org.AlGelsin.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service",url = "http://localhost:8082/api/v1/user")
public interface UserFeignClient {
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequestDto request);

}
