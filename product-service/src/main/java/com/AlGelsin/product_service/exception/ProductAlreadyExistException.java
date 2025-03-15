package com.AlGelsin.product_service.exception;

public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String s) {
        super(s);
    }
}
