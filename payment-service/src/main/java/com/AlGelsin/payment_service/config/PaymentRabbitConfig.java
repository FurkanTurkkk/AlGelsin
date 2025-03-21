package com.AlGelsin.payment_service.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentRabbitConfig {

    public static final String EXCHANGE_NAME = "common-exchange";
    public static final String ROUTING_KEY_INCREASE_STOCK = "increase-stock";
    public static final String ROUTING_KEY_COMPLETED = "order.completed";
    public static final String ROUTING_KEY_CANCELED = "order.canceled";
    public static final String ROUTING_KEY_NOTIFICATION = "notification";

    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
