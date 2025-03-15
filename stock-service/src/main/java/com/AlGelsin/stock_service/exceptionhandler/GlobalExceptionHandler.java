package com.AlGelsin.stock_service.exceptionhandler;

import com.AlGelsin.stock_service.exception.StockInformationNotFoundByProductId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StockInformationNotFoundByProductId.class)
    public ResponseEntity<String> handleStockInformationNotFoundByProductId(StockInformationNotFoundByProductId e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
