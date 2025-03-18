package com.AlGelsin.order_service.repository;

import com.AlGelsin.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,String> {
    List<Order> findByUserId(String userId);
    Optional<Order> findByCartId(String cartId);
}
