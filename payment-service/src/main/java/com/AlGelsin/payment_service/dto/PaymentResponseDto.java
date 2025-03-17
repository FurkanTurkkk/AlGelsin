package com.AlGelsin.payment_service.dto;

public class PaymentResponseDto {
    private boolean success;
    private String message;
    private String transactionId;

    public PaymentResponseDto() {
    }

    public PaymentResponseDto(boolean success, String message, String transactionId) {
        this.success = success;
        this.message = message;
        this.transactionId = transactionId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
