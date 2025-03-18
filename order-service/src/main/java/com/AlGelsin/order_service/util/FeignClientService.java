package com.AlGelsin.order_service.util;

import com.AlGelsin.order_service.feignclient.CartFeignClient;
import com.AlGelsin.order_service.feignclient.UserFeignClient;
import org.AlGelsin.CartDto;
import org.AlGelsin.UserDto;
import org.springframework.stereotype.Component;

@Component
public class FeignClientService {

    private final CartFeignClient cartFeignClient;
    private final UserFeignClient userFeignClient;

    public FeignClientService(CartFeignClient cartFeignClient, UserFeignClient userFeignClient) {
        this.cartFeignClient = cartFeignClient;
        this.userFeignClient = userFeignClient;
    }

    public CartDto getCartByAuthId(Long authId){
        return cartFeignClient.getCartByUserId(authId).getBody();
    }

    public void deleteCartByAuthId(Long authId){
        cartFeignClient.deleteCartByAuthId(authId);
    }

    public UserDto getUserDtoByAuthId(Long authId){
        return userFeignClient.getUserByAuthId(authId).getBody();
    }
}
