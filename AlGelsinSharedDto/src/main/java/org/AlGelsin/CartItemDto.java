package org.AlGelsin;

import java.math.BigDecimal;

public class CartItemDto {

    private String productId;
    private int quantity;
    private BigDecimal price;

    public CartItemDto(){

    }

    public CartItemDto(String productId,int quantity,BigDecimal price){
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
