package com.AlGelsin.order_service.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;
    private String cartId;
    private BigDecimal totalPrice;
    private final LocalDate createdAt=LocalDate.now();
    @Enumerated(EnumType.STRING)
    private Statu statu;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
    List<OrderItem> orderItemList = new ArrayList<>();

    public Order(){

    }

    public Order(String userId,String cartId,BigDecimal totalPrice){
        this.userId = userId;
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.statu = Statu.PENDING;
    }

    public String getId() {
        return id;
    }

    public String getCartId() {
        return cartId;
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

    public Statu getStatu() {
        return statu;
    }

    public void setStatu(Statu statu) {
        this.statu = statu;
    }
}
