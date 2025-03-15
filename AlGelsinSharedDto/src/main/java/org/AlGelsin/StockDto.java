package org.AlGelsin;

public class StockDto {

    private Long id;
    private String productName;
    private int quantity;

    public StockDto() {
    }

    public StockDto(Long id, String productName, int quantity) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}
