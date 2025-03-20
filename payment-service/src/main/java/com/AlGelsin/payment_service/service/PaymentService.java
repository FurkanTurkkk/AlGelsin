package com.AlGelsin.payment_service.service;

import com.AlGelsin.payment_service.config.IyzicoConfig;
import com.AlGelsin.payment_service.config.PaymentRabbitConfig;
import com.AlGelsin.payment_service.dto.PaymentRequestDto;
import com.AlGelsin.payment_service.dto.PaymentResult;
import com.AlGelsin.payment_service.util.FeignClientService;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.AlGelsin.*;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class PaymentService {
    private final static Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final IyzicoConfig iyzicoConfig;
    private final RabbitTemplate rabbitTemplate;
    private final HttpServletRequest request;
    private final FeignClientService feignClientService;


    public PaymentService(IyzicoConfig iyzicoConfig, RabbitTemplate rabbitTemplate, HttpServletRequest request, FeignClientService feignClientService) {
        this.iyzicoConfig = iyzicoConfig;
        this.rabbitTemplate = rabbitTemplate;
        this.request = request;
        this.feignClientService = feignClientService;
    }


    public CreatePaymentRequest createPaymentRequestFromDto(PaymentRequestDto requestDto,Long authId){
        String userIp = request.getRemoteAddr();
        CreatePaymentRequest paymentRequest = new CreatePaymentRequest();
        UserDto userDto = feignClientService.getUserDtoByAuthId(authId);
        AuthDto authDto = feignClientService.getAuthDtoByAuthId(authId);
        CartDto cartDto = feignClientService.getCartByAuthId(authId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
        buyer.setLastLoginDate(authDto.getLastLoginDate().format(formatter));
        buyer.setRegistrationDate(authDto.getRegistrationDate().format(formatter));
        buyer.setRegistrationAddress("-");
        buyer.setIp(userIp);
        buyer.setCity(requestDto.getBillingAddress().getCity());
        buyer.setCountry(requestDto.getBillingAddress().getCountry());
        buyer.setZipCode(requestDto.getBillingAddress().getZipCode());
        paymentRequest.setBuyer(buyer);

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

    public PaymentResult processPayment(PaymentRequestDto requestDto, Long authId) {
        CreatePaymentRequest paymentRequest = createPaymentRequestFromDto(requestDto, authId);
        logger.info("Ödeme işlemi başlatılıyor. AuthId: {}", authId);

        try {

            Payment iyzicoPayment = Payment.create(paymentRequest,iyzicoConfig.options());

            if ("SUCCESS".equalsIgnoreCase(iyzicoPayment.getStatus())) {
                logger.info("Ödeme başarılı. Payment ID: {}", iyzicoPayment.getPaymentId());

                rabbitTemplate.convertAndSend(PaymentRabbitConfig.EXCHANGE_NAME,
                        PaymentRabbitConfig.ROUTING_KEY_COMPLETED,
                        authId);

                return new PaymentResult(true, "Ödeme başarıyla gerçekleştirildi", iyzicoPayment.getPaymentId());
            } else {
                logger.error("Ödeme başarısız: {}", iyzicoPayment.getErrorMessage());

                rabbitTemplate.convertAndSend(PaymentRabbitConfig.EXCHANGE_NAME,
                        PaymentRabbitConfig.ROUTING_KEY_CANCELED,
                        authId);
                return new PaymentResult(false, "Ödeme başarısız: " + iyzicoPayment.getErrorMessage(), null);
            }
        } catch (Exception e) {
            logger.error("Iyzico ödeme işlemi sırasında hata oluştu: ", e);
            return new PaymentResult(false, "Ödeme işlemi sırasında hata oluştu", null);
        }
    }
}
