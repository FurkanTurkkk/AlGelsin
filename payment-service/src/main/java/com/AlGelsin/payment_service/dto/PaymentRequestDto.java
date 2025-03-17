package com.AlGelsin.payment_service.dto;


import org.AlGelsin.AddressDto;
import org.AlGelsin.ProductDto;
import org.AlGelsin.UserDto;

import java.math.BigDecimal;
import java.util.List;

public class PaymentRequestDto {
    private String locale;
    private String conversationId;
    private BigDecimal price;
    private BigDecimal paidPrice;
    private String currency;
    private int installment;
    private List<ProductDto> products;
    private UserDto user;
    private AddressDto shippingAddress;
    private AddressDto billingAddress;

    public PaymentRequestDto() {
    }

    public PaymentRequestDto(String locale,
                             String conversationId,
                             BigDecimal price,
                             BigDecimal paidPrice,
                             String currency,
                             int installment,
                             List<ProductDto> products,
                             UserDto user,
                             AddressDto shippingAddress,
                             AddressDto billingAddress) {
        this.locale = locale;
        this.conversationId = conversationId;
        this.price = price;
        this.paidPrice = paidPrice;
        this.currency = currency;
        this.installment = installment;
        this.products = products;
        this.user = user;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    public String getLocale() {
        return locale;
    }

    public String getConversationId() {
        return conversationId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public int getInstallment() {
        return installment;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public UserDto getUser() {
        return user;
    }

    public AddressDto getShippingAddress() {
        return shippingAddress;
    }

    public AddressDto getBillingAddress() {
        return billingAddress;
    }
}
