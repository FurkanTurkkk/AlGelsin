package com.AlGelsin.cart_service.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;
    private int quantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public CartItem(){

    }

    public CartItem(String productId, int quantity, BigDecimal price,Cart cart) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.cart = cart;
    }

    public Long getId() {
        return id;
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

    public Cart getCart() {
        return cart;
    }

    public void updatePrice(BigDecimal price){
        this.price = price;
    }

    public void updateQuantity(int quantity){
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(productId, cartItem.productId) && Objects.equals(cart, cartItem.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, cart);
    }
}
