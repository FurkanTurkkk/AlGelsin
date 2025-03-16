package com.AlGelsin.cart_service.converter;

import com.AlGelsin.cart_service.model.Cart;
import org.AlGelsin.CartDto;
import org.springframework.stereotype.Component;

@Component
public class CartDtoConverter {
    private final CartItemDtoConverter converter;

    public CartDtoConverter(CartItemDtoConverter converter) {
        this.converter = converter;
    }

    public CartDto convert(Cart cart){
        return new CartDto(
                cart.getTotalPrice(),
                cart.getCartItemList().stream()
                        .map(converter::convert)
                        .toList()
        );
    }
}
