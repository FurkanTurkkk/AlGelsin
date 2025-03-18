package com.AlGelsin.payment_service.feignclient;

import org.AlGelsin.AuthDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service",url = "http://localhost:8081/api/v1/auth")
public interface AuthFeignClient {
    @GetMapping("/get")
    public ResponseEntity<AuthDto> getInformationById(@RequestHeader("Auth-Id")Long authId);
}
