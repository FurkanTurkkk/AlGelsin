package com.AlGelsin.payment_service.feignclient;

import org.AlGelsin.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service",url = "http://localhost:8082/api/v1/user")
public interface UserFeignClient {
    @GetMapping("/find-user")
    public ResponseEntity<UserDto> getUserByAuthId(@RequestHeader("Auth-Id")Long authId);


}
