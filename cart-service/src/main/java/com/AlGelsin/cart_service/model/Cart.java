package com.AlGelsin.cart_service.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private BigDecimal totalPrice;

    @Column(nullable = false)
    private String userId;
    private String orderId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    List<CartItem> cartItemList = new ArrayList<>();

    public Cart(){

    }

    public Cart(BigDecimal totalPrice, String userId) {
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getUserId() {
        return userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void updatePrice(BigDecimal totalPrice){
        this.totalPrice = totalPrice;
    }

    public void setOrderId(String orderId){
        this.orderId = orderId;
    }
}
