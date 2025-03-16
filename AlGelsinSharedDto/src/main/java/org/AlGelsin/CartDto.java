package org.AlGelsin;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {

    private BigDecimal totalPrice;
    private List<CartItemDto> cartItemDtoList;

    public CartDto(){

    }

    public CartDto(BigDecimal totalPrice,List<CartItemDto> cartItemDtoList){
        this.totalPrice = totalPrice;
        this.cartItemDtoList = cartItemDtoList;
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<CartItemDto> getCartItemDtoList() {
        return cartItemDtoList;
    }
}
