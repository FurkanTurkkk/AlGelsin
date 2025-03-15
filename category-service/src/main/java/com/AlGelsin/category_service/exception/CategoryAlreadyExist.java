package com.AlGelsin.category_service.exception;

public class CategoryAlreadyExist extends RuntimeException {
    public CategoryAlreadyExist(String s) {
        super(s);
    }
}
