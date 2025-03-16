package com.AlGelsin.cart_service.controller;

import com.AlGelsin.cart_service.service.CartService;
import org.AlGelsin.CartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/get-cart")
    public ResponseEntity<CartDto> getCartByUserId(@RequestHeader("Auth-Id")Long authId){
        return ResponseEntity.ok(cartService.getCartByUserId(authId));
    }
}
