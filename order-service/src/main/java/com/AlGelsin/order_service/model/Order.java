package com.AlGelsin.order_service.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;
    private BigDecimal totalPrice;
    private final LocalDate createdAt=LocalDate.now();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
    List<OrderItem> orderItemList = new ArrayList<>();

    public Order(){

    }

    public Order(String userId,BigDecimal totalPrice){
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }
}
