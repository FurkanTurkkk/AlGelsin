package com.AlGelsin.cart_service.converter;

import com.AlGelsin.cart_service.model.CartItem;
import com.AlGelsin.cart_service.util.FeignClientService;
import org.AlGelsin.CartItemDto;
import org.springframework.stereotype.Component;

@Component
public class CartItemDtoConverter {
    private final FeignClientService feignClientService;

    public CartItemDtoConverter(FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    public CartItemDto convert(CartItem cartItem){
        String productName = feignClientService.getProductNameByProductId(cartItem.getProductId());
        return new CartItemDto(
                productName,
                cartItem.getQuantity(),
                cartItem.getPrice()
        );
    }
}
