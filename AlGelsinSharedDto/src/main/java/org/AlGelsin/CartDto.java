package org.AlGelsin;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {

    private String id;
    private BigDecimal totalPrice;
    private List<CartItemDto> cartItemDtoList;

    public CartDto(){

    }

    public CartDto(String id,BigDecimal totalPrice,List<CartItemDto> cartItemDtoList){
        this.id = id;
        this.totalPrice = totalPrice;
        this.cartItemDtoList = cartItemDtoList;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<CartItemDto> getCartItemDtoList() {
        return cartItemDtoList;
    }
}
