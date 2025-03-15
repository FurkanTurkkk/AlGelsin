package com.AlGelsin.user_service.exception;

public class EmailAddressAlreadyExist extends RuntimeException {
    public EmailAddressAlreadyExist(String s) {
        super(s);
    }
}
