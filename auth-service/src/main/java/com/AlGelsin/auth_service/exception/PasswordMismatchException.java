package com.AlGelsin.auth_service.exception;

public class PasswordMismatchException extends RuntimeException{
    public PasswordMismatchException(String s){
        super(s);
    }
}
