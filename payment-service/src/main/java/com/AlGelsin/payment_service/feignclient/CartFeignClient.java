package com.AlGelsin.payment_service.feignclient;

import org.AlGelsin.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "cart-service",url = "http://localhost:8086/api/v1/cart")
public interface CartFeignClient {
    @GetMapping("/get-cart")
    public ResponseEntity<CartDto> getCartByUserId(@RequestHeader("Auth-Id")Long authId);

    @DeleteMapping
    public void deleteCartByAuthId(@RequestHeader("Auth-Id")Long authId);
}
