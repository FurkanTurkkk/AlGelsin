package com.AlGelsin.cart_service.controller;

import com.AlGelsin.cart_service.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart-item")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> addCartItem(@RequestHeader("Auth-Id")Long authId,
                                              @RequestParam String productId,
                                              @RequestParam int quantity){
        cartItemService.addCartItem(authId,productId,quantity);
        return ResponseEntity.ok("Item added successfully");
    }
}
