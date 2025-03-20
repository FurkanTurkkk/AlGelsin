package com.AlGelsin.order_service.service;

import com.AlGelsin.order_service.config.OrderRabbitConfig;
import com.AlGelsin.order_service.exception.OrderNotFoundException;
import com.AlGelsin.order_service.model.Order;
import com.AlGelsin.order_service.model.Statu;
import com.AlGelsin.order_service.repository.OrderRepository;
import com.AlGelsin.order_service.util.FeignClientService;
import org.AlGelsin.CartDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderService {
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;
    private final FeignClientService feignClientService;
    private final RabbitTemplate rabbitTemplate;

    public OrderService(OrderItemService orderItemService, OrderRepository orderRepository, FeignClientService feignClientService, RabbitTemplate rabbitTemplate) {
        this.orderItemService = orderItemService;
        this.orderRepository = orderRepository;
        this.feignClientService = feignClientService;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void createOrder(Long authId){
        Order order = orderItemService.convertToOrderItemFromCartItem(authId);
        Optional<Order> registeredOrder = orderRepository.findByCartId(order.getCartId());
        if(registeredOrder.isEmpty()){
            orderRepository.save(order);
        }else{
            orderRepository.save(registeredOrder.get());
        }
    }

    @RabbitListener(queues = OrderRabbitConfig.ORDER_COMPLETED_QUEUE)
    public void updateOrderStatuFromPendingToCompleted(Long authId){
        CartDto cartDto = feignClientService.getCartByAuthId(authId);
        Optional<Order> order = orderRepository.findByCartId(cartDto.getId());
        if(order.isEmpty()){
            throw new OrderNotFoundException("Order could not found by cart id : "+cartDto.getId());
        }
        Order existingOrder = order.get();
        existingOrder.setStatu(Statu.COMPLETED);
        orderRepository.save(existingOrder);
        rabbitTemplate.convertAndSend(OrderRabbitConfig.EXCHANGE_NAME,
                OrderRabbitConfig.ROUTING_KEY_CART,
                authId);
    }

    @RabbitListener(queues = OrderRabbitConfig.ORDER_CANCELED_QUEUE)
    public void updateOrderStatuFromPendingToCanceled(Long authId){
        CartDto cartDto = feignClientService.getCartByAuthId(authId);
        Optional<Order> order = orderRepository.findByCartId(cartDto.getId());
        if(order.isEmpty()){
            throw new OrderNotFoundException("Order could not found by cart id : "+cartDto.getId());
        }
        Order existingOrder = order.get();
        existingOrder.setStatu(Statu.CANCELED);
        orderRepository.save(existingOrder);
    }
}
