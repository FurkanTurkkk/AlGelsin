package com.AlGelsin.cart_service.service;

import com.AlGelsin.cart_service.converter.CartDtoConverter;
import com.AlGelsin.cart_service.model.Cart;
import com.AlGelsin.cart_service.model.CartItem;
import com.AlGelsin.cart_service.repository.CartRepository;
import com.AlGelsin.cart_service.util.FeignClientService;
import org.AlGelsin.CartDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class CartService {
    private final CartRepository cartRepository;
    private final FeignClientService feignClientService;
    private final CartDtoConverter converter;

    public CartService(CartRepository cartRepository, FeignClientService feignClientService, CartDtoConverter converter) {
        this.cartRepository = cartRepository;
        this.feignClientService = feignClientService;
        this.converter = converter;
    }

    public Cart createOrGetCart(String userId,BigDecimal price){
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if(cart.isPresent()){
            return cart.get();
        }
        return cartRepository.save(new Cart(
                price,
                userId
        ));
    }

    public void calculateCartPrice(Cart cart){
        BigDecimal price = BigDecimal.ZERO;
        List<CartItem> cartItemList = cart.getCartItemList();
        for (CartItem cartItem : cartItemList) {
            price = price.add(cartItem.getPrice());
        }
        cart.updatePrice(price);
        cartRepository.save(cart);
    }

    public CartDto getCartByUserId(Long authId) {
        String userId = feignClientService.getUserIdByAuthId(authId);
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if(cart.isPresent()){
            return converter.convert(cart.get());
        }
        throw new RuntimeException("You don't have any Cart");
    }
}
