package com.AlGelsin.order_service.repository;

import com.AlGelsin.order_service.model.Order;
import com.AlGelsin.order_service.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    Optional<OrderItem> findByOrder(Order order);
}
