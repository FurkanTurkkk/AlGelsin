package com.AlGelsin.cart_service.repository;

import com.AlGelsin.cart_service.model.Cart;
import com.AlGelsin.cart_service.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCartAndProductId(Cart cart, String productId);
}
