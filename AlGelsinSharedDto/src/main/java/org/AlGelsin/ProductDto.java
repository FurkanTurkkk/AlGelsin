package org.AlGelsin;

import java.math.BigDecimal;

public class ProductDto {

    private String id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String description;

    public ProductDto(){}

    public ProductDto(String id,String name,BigDecimal price,int quantity,String description){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
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
