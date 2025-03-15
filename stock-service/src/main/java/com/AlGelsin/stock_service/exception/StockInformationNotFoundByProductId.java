package com.AlGelsin.stock_service.exception;

public class StockInformationNotFoundByProductId extends RuntimeException {
    public StockInformationNotFoundByProductId(String s) {
        super(s);
    }
}
