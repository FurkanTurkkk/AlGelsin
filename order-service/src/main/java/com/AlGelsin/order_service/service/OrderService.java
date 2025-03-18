package com.AlGelsin.order_service.service;

import com.AlGelsin.order_service.converter.OrderDtoConverter;
import com.AlGelsin.order_service.exception.OrderNotFoundException;
import com.AlGelsin.order_service.model.Order;
import com.AlGelsin.order_service.repository.OrderRepository;
import com.AlGelsin.order_service.util.FeignClientService;
import org.AlGelsin.OrderDto;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderService {
    private final OrderItemService orderItemService;
    private final FeignClientService feignClientService;
    private final OrderRepository orderRepository;
    private final OrderDtoConverter converter;

    public OrderService(OrderItemService orderItemService, FeignClientService feignClientService, OrderRepository orderRepository, OrderDtoConverter converter) {
        this.orderItemService = orderItemService;
        this.feignClientService = feignClientService;
        this.orderRepository = orderRepository;
        this.converter = converter;
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

    public OrderDto getOrderByCartId(String cartId){
        Optional<Order> order = orderRepository.findByCartId(cartId);

        if(order.isEmpty()){
            throw new OrderNotFoundException("Order could not found by cart id : "+cartId);
        }

        return converter.convert(order.get());
    }
}
