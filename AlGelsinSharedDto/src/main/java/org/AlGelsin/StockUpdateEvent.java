package org.AlGelsin;

import java.io.Serializable;

public class StockUpdateEvent {
    private String productId;
    private int quantity;

    public StockUpdateEvent() {
    }

    // Constructor
    public StockUpdateEvent(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getter-Setter
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
