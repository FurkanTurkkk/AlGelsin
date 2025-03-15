package com.AlGelsin.stock_service.dto;

public class CreateStockRequestDto {

    private String productId;
    private int quantity;

    public CreateStockRequestDto() {
    }

    public CreateStockRequestDto(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
