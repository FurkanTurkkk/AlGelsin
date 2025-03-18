package com.AlGelsin.order_service.converter;

import com.AlGelsin.order_service.model.OrderItem;
import org.AlGelsin.OrderItemDto;
import org.springframework.stereotype.Component;

@Component
public class OrderItemDtoConverter {

    public OrderItemDto convert(OrderItem orderItem){
        return new OrderItemDto(
                orderItem.getProductId(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}
