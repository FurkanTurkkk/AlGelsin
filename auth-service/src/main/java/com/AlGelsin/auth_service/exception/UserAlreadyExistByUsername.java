package com.AlGelsin.auth_service.exception;

public class UserAlreadyExistByUsername extends RuntimeException {
    public UserAlreadyExistByUsername(String s) {
        super(s);
    }
}
