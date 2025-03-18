package com.AlGelsin.order_service.converter;

import com.AlGelsin.order_service.model.Order;
import org.AlGelsin.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoConverter {
    private final OrderItemDtoConverter converter;

    public OrderDtoConverter(OrderItemDtoConverter converter) {
        this.converter = converter;
    }

    public OrderDto convert(Order order){
        return new OrderDto(
                order.getUserId(),
                order.getTotalPrice(),
                order.getOrderItemList().stream()
                        .map(converter::convert)
                        .toList()
        );
    }
}
