package com.AlGelsin.cart_service.repository;

import com.AlGelsin.cart_service.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,String> {
    Optional<Cart> findByUserId(String userId);
}
