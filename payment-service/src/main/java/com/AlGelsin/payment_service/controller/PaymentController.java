package com.AlGelsin.payment_service.controller;

import com.AlGelsin.payment_service.dto.PaymentRequestDto;
import com.AlGelsin.payment_service.dto.PaymentResponseDto;
import com.AlGelsin.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final static Logger logger = LoggerFactory.getLogger(PaymentController.class);

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public ResponseEntity<?> processPayment(@Valid @RequestBody PaymentRequestDto request,
                                                             @RequestHeader("Auth-Id")Long authId) {
        logger.info("Received payment request for order: {}");
        return ResponseEntity.ok(paymentService.createPaymentRequestFromDto(request,authId));
    }
}
