package org.AlGelsin;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    private String userId;
    private BigDecimal totalPrice;
    private List<OrderItemDto> orderItemList;

    public OrderDto() {
    }

    public OrderDto(String userId, BigDecimal totalPrice, List<OrderItemDto> orderItemList) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.orderItemList = orderItemList;
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<OrderItemDto> getOrderItemList() {
        return orderItemList;
    }
}
