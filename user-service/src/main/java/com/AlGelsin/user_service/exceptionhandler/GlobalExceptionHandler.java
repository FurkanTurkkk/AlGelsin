package com.AlGelsin.user_service.exceptionhandler;

import com.AlGelsin.user_service.exception.EmailAddressAlreadyExist;
import com.AlGelsin.user_service.exception.UserNotFoundByAuthIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAddressAlreadyExist.class)
    public ResponseEntity<String> handleEmailAddressAlreadyExist(EmailAddressAlreadyExist e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundByAuthIdException.class)
    public ResponseEntity<String> handleUserNotFoundByAuthIdException(UserNotFoundByAuthIdException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
        System.err.println("Error occurred: " + ex.getMessage());
        return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
