package com.AlGelsin.payment_service.dto;


import org.AlGelsin.AddressDto;

public class PaymentRequestDto {
    private AddressDto shippingAddress;
    private AddressDto billingAddress;

    public PaymentRequestDto() {
    }

    public PaymentRequestDto(AddressDto shippingAddress,
                             AddressDto billingAddress) {
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    public AddressDto getShippingAddress() {
        return shippingAddress;
    }

    public AddressDto getBillingAddress() {
        return billingAddress;
    }
}
