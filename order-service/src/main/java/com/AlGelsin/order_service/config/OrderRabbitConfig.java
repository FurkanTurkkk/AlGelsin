package com.AlGelsin.order_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRabbitConfig {
    public static final String EXCHANGE_NAME = "common-exchange";
    public static final String ORDER_COMPLETED_QUEUE = "order-completed-queue";
    public static final String ORDER_CANCELED_QUEUE = "order-canceled-queue";
    public static final String ROUTING_KEY_CART= "cart";
    public static final String ROUTING_KEY_COMPLETED = "order.completed";
    public static final String ROUTING_KEY_CANCELED = "order.canceled";

    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue orderCompletedQueue() {
        return new Queue(ORDER_COMPLETED_QUEUE, true);
    }

    @Bean
    public Queue orderCanceledQueue() {
        return new Queue(ORDER_CANCELED_QUEUE, true);
    }

    @Bean
    public Binding orderCompletedBinding(DirectExchange commonExchange, Queue orderCompletedQueue) {
        return BindingBuilder.bind(orderCompletedQueue).to(commonExchange).with(ROUTING_KEY_COMPLETED);
    }

    @Bean
    public Binding orderCanceledBinding(DirectExchange commonExchange, Queue orderCanceledQueue) {
        return BindingBuilder.bind(orderCanceledQueue).to(commonExchange).with(ROUTING_KEY_CANCELED);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
