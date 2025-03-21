package com.AlGelsin.payment_service.dto;


import org.AlGelsin.AddressDto;

public class PaymentRequestDto {
    private AddressDto shippingAddress;
    private AddressDto billingAddress;
    private String cartHolderName;
    private String cardNumber;
    private String expireMonth;
    private String expireYear;
    private String cvc;
    private Integer registerCard;


    public PaymentRequestDto() {
    }

    public PaymentRequestDto(AddressDto shippingAddress, AddressDto billingAddress, String cartHolderName, String cardNumber, String expireMonth, String expireYear, String cvc, Integer registerCard) {
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.cartHolderName = cartHolderName;
        this.cardNumber = cardNumber;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.cvc = cvc;
        this.registerCard = registerCard;
    }

    public AddressDto getShippingAddress() {
        return shippingAddress;
    }

    public AddressDto getBillingAddress() {
        return billingAddress;
    }

    public String getCartHolderName() {
        return cartHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpireMonth() {
        return expireMonth;
    }

    public String getExpireYear() {
        return expireYear;
    }

    public String getCvc() {
        return cvc;
    }

    public Integer getRegisterCard() {
        return registerCard;
    }
}
