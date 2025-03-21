package com.AlGelsin.notification_service.config;


import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class NotificationRabbitConfig {

    public static final String EXCHANGE_NAME = "common-exchange";
    public static final String MAIL_QUEUE = "completed-mail-queue";
    public static final String ROUTING_KEY_NOTIFICATION = "notification";


    @Bean
    public DirectExchange commonExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue notificationQueue(){
        return new Queue(MAIL_QUEUE,true);
    }

    @Bean
    public Binding notificationBinding(DirectExchange commonExchange, Queue notificationQueue){
        return BindingBuilder.bind(notificationQueue).to(commonExchange).with(ROUTING_KEY_NOTIFICATION);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
