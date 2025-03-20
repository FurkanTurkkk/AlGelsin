package com.AlGelsin.cart_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartRabbitConfig {
    public static final String EXCHANGE_NAME = "common-exchange";
    public static final String CART_QUEUE = "cart-queue";
    public static final String ROUTING_KEY_CART = "cart";

    @Bean
    public DirectExchange commonExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue cartQueue() {
        return new Queue(CART_QUEUE, true);
    }

    @Bean
    public Binding cartBinding(DirectExchange commonExchange, Queue cartQueue) {
        return BindingBuilder.bind(cartQueue).to(commonExchange).with(ROUTING_KEY_CART);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
