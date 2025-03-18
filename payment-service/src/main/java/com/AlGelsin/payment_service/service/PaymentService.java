package com.AlGelsin.payment_service.service;

import com.AlGelsin.payment_service.config.IyzicoConfig;
import com.AlGelsin.payment_service.dto.PaymentRequestDto;
import com.AlGelsin.payment_service.util.FeignClientService;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.AlGelsin.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;


@Service
public class PaymentService {

    private final IyzicoConfig iyzicoConfig;
    private final static Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final HttpServletRequest request;
    private final FeignClientService feignClientService;


    public PaymentService(IyzicoConfig iyzicoConfig, HttpServletRequest request, FeignClientService feignClientService) {
        this.iyzicoConfig = iyzicoConfig;
        this.request = request;
        this.feignClientService = feignClientService;
    }


    public CreatePaymentRequest createPaymentRequestFromDto(PaymentRequestDto requestDto,Long authId){
        String userIp = request.getRemoteAddr();
        CreatePaymentRequest paymentRequest = new CreatePaymentRequest();
        UserDto userDto = feignClientService.getUserDtoByAuthId(authId);
        AuthDto authDto = feignClientService.getAuthDtoByAuthId(authId);
        CartDto cartDto = feignClientService.getCartByAuthId(authId);
        // Locale ve ödeme bilgilerini ayarla
        paymentRequest.setLocale(Locale.TR.getValue());
        paymentRequest.setConversationId("123456789");
        paymentRequest.setPrice(cartDto.getTotalPrice());
        paymentRequest.setPaidPrice(cartDto.getTotalPrice());
        paymentRequest.setCurrency(Currency.TRY.name());
        paymentRequest.setInstallment(1);
        paymentRequest.setPaymentChannel(PaymentChannel.WEB.name());
        paymentRequest.setPaymentGroup(PaymentGroup.PRODUCT.name());


        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardHolderName("John Doe");
        paymentCard.setCardNumber("5528790000000008");
        paymentCard.setExpireMonth("12");
        paymentCard.setExpireYear("2030");
        paymentCard.setCvc("123");
        paymentCard.setRegisterCard(0);
        paymentRequest.setPaymentCard(paymentCard);

        Buyer buyer = new Buyer();
        buyer.setId(userDto.getId());
        buyer.setName(userDto.getName());
        buyer.setSurname(userDto.getSurname());
        buyer.setGsmNumber(userDto.getPhone());
        buyer.setEmail(userDto.getEmail());
        buyer.setIdentityNumber(userDto.getTc());
        buyer.setLastLoginDate(authDto.getLastLoginDate().toString());
        buyer.setRegistrationDate(authDto.getRegistrationDate().toString());
        buyer.setRegistrationAddress("-");
        buyer.setIp(userIp);
        buyer.setCity(requestDto.getBillingAddress().getCity());
        buyer.setCity(requestDto.getBillingAddress().getCountry());
        buyer.setZipCode(requestDto.getBillingAddress().getZipCode());
        paymentRequest.setBuyer(buyer);


        // Sepet ürünlerini (order items) dönüştür


        List<BasketItem> basketItems = new ArrayList<>();
        List<CartItemDto> cartItemDtoList = cartDto.getCartItemDtoList();
        for (CartItemDto cartItemDto : cartItemDtoList) {
            BasketItem item = new BasketItem();
            item.setId(cartItemDto.getProductId());
            item.setName(feignClientService.getProductNameByProductId(cartItemDto.getProductId()));
            item.setCategory1("-");
            item.setItemType(BasketItemType.PHYSICAL.name());
            item.setPrice(cartItemDto.getPrice());
            basketItems.add(item);
        }
        paymentRequest.setBasketItems(basketItems);

        // Teslimat ve fatura adreslerini ayarla
        AddressDto shippingAddressDto = requestDto.getShippingAddress();
        Address shippingAddress = new Address();
        shippingAddress.setContactName(userDto.getName());
        shippingAddress.setCity(shippingAddressDto.getCity());
        shippingAddress.setCountry(shippingAddressDto.getCountry());
        shippingAddress.setAddress(shippingAddressDto.getCountry()+shippingAddressDto.getCity());
        shippingAddress.setZipCode(shippingAddressDto.getZipCode());
        paymentRequest.setShippingAddress(shippingAddress);


        AddressDto billingAddressDto = requestDto.getBillingAddress();
        Address billingAddress = new Address();
        billingAddress.setContactName(userDto.getName());
        billingAddress.setCity(billingAddressDto.getCity());
        billingAddress.setCountry(billingAddressDto.getCountry());
        billingAddress.setAddress(billingAddressDto.getCity());
        billingAddress.setZipCode(billingAddressDto.getZipCode());
        paymentRequest.setBillingAddress(billingAddress);

        return paymentRequest;
    }
}
