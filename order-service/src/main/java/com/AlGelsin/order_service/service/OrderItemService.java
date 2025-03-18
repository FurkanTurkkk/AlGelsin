package com.AlGelsin.order_service.service;

import com.AlGelsin.order_service.model.Order;
import com.AlGelsin.order_service.model.OrderItem;
import com.AlGelsin.order_service.repository.OrderItemRepository;
import com.AlGelsin.order_service.util.FeignClientService;
import org.AlGelsin.CartDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final FeignClientService feignClientService;

    public OrderItemService(OrderItemRepository orderItemRepository, FeignClientService feignClientService) {
        this.orderItemRepository = orderItemRepository;
        this.feignClientService = feignClientService;
    }

    public Order convertToOrderItemFromCartItem(Long authId){
        CartDto cartDto = feignClientService.getCartByAuthId(authId);
        String userId = feignClientService.getUserDtoByAuthId(authId).getId();
        Order order = new Order(userId,cartDto.getId(),cartDto.getTotalPrice());
        cartDto.getCartItemDtoList().forEach(cartItemDto -> {
                    OrderItem orderItem =new OrderItem(
                            cartItemDto.getProductId(),
                            cartItemDto.getQuantity(),
                            cartItemDto.getPrice(),
                            order
                    );
                    order.getOrderItemList().add(orderItem);
                });
        return order;
    }

}
