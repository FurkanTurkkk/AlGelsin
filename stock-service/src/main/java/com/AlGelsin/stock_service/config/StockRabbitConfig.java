package com.AlGelsin.stock_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockRabbitConfig {

    public static final String EXCHANGE_NAME = "common-exchange";
    public static final String ROUTING_KEY_INCREASE_STOCK = "increase-stock";
    public static final String INCREASE_STOCK_QUEUE = "increase-stock-queue";
    public static final String ROUTING_KEY_CART= "cart";

    @Bean
    public DirectExchange commonExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue orderCompletedQueue() {
        return new Queue(INCREASE_STOCK_QUEUE, true);
    }

    @Bean
    public Binding orderCompletedBinding(DirectExchange commonExchange, Queue increaseStockQueue) {
        return BindingBuilder.bind(increaseStockQueue).to(commonExchange).with(ROUTING_KEY_INCREASE_STOCK);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
