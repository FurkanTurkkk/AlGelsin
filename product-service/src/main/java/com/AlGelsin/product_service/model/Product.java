package com.AlGelsin.product_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "product")
public class Product {

    @Id
    private String id;

    private String name;
    private List<Long> categoryIds=new ArrayList<>();
    private BigDecimal price;
    private String description;

    public Product() {
    }

    public Product(String name, List<Long> categoryIds, BigDecimal price, String description) {
        this.name = name;
        this.categoryIds = categoryIds;
        this.price = price;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
