package com.AlGelsin.product_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class CreateProductRequestDto {

    @NotBlank(message = "Product must have name")
    private String name;
    @NotEmpty(message = "Product must have category")
    private List<Long> categoryList;
    @NotNull(message = "Product must have price")
    private BigDecimal price;
    @NotNull(message = "Quantity can not null")
    private int quantity;

    private String description;

    public CreateProductRequestDto() {
    }

    public CreateProductRequestDto(String name, List<Long> categoryList, BigDecimal price, int quantity,String description) {
        this.name = name;
        this.categoryList = categoryList;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public List<Long> getCategoryList() {
        return categoryList;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }
}
