package com.AlGelsin.stock_service.dto;

public class UpdateStockRequestDto {

    private String productId;
    private int quantity;

    public UpdateStockRequestDto() {
    }

    public UpdateStockRequestDto(String productId, int quantity) {
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
