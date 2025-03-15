package com.AlGelsin.user_service.exception;

public class UserNotFoundByAuthIdException extends RuntimeException {
    public UserNotFoundByAuthIdException(String s) {
        super(s);
    }
}
