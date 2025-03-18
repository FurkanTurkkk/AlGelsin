package com.AlGelsin.cart_service.controller;

import com.AlGelsin.cart_service.service.CartService;
import org.AlGelsin.CartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping
    public void deleteCartByAuthId(@RequestHeader("Auth-Id")Long authId){
        cartService.deleteCartByAuthId(authId);
    }
}
