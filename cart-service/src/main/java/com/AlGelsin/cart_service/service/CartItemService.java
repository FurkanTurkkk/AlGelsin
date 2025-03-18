package com.AlGelsin.cart_service.service;

import com.AlGelsin.cart_service.model.Cart;
import com.AlGelsin.cart_service.model.CartItem;
import com.AlGelsin.cart_service.repository.CartItemRepository;
import com.AlGelsin.cart_service.util.FeignClientService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartService cartService;
    private final FeignClientService feignClientService;

    public CartItemService(CartItemRepository cartItemRepository,
                           CartService cartService,
                           FeignClientService feignClientService) {
        this.cartItemRepository = cartItemRepository;
        this.cartService = cartService;
        this.feignClientService = feignClientService;
    }

    public void addCartItem(Long authId,String productId,int quantity) {
        String userId = feignClientService.getUserIdByAuthId(authId);
        BigDecimal price = calculateCartItemPrice(productId,quantity);
        Cart cart = cartService.createOrGetCart(userId,price);
        Optional<CartItem> cartItem = cartItemRepository.findByCartAndProductId(cart,productId);
        if(cartItem.isPresent()){
            CartItem existingCartItem = cartItem.get();
            existingCartItem.updateQuantity(existingCartItem.getQuantity()+quantity);
            BigDecimal updatedPrice = calculateCartItemPrice(productId,existingCartItem.getQuantity());
            existingCartItem.updatePrice(updatedPrice);
            cartItemRepository.save(existingCartItem);
            cartService.calculateCartPrice(cart);
            return;
        }else {
            CartItem createdCartItem = new CartItem(
                    productId,
                    quantity,
                    price,
                    cart
            );
            cart.getCartItemList().add(createdCartItem);
            cartItemRepository.save(createdCartItem);
        }

        cartService.calculateCartPrice(cart);
    }

    private BigDecimal calculateCartItemPrice(String productId, int quantity) {
        BigDecimal productPrice = feignClientService.getProductPrice(productId);
        return productPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
