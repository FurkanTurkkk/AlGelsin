package com.AlGelsin.payment_service.util;

import com.AlGelsin.payment_service.feignclient.AuthFeignClient;
import com.AlGelsin.payment_service.feignclient.CartFeignClient;
import com.AlGelsin.payment_service.feignclient.ProductFeignClient;
import com.AlGelsin.payment_service.feignclient.UserFeignClient;
import org.AlGelsin.AuthDto;
import org.AlGelsin.CartDto;
import org.AlGelsin.UserDto;
import org.springframework.stereotype.Component;

@Component
public class FeignClientService {

    private final UserFeignClient userFeignClient;
    private final CartFeignClient cartFeignClient;
    private final ProductFeignClient productFeignClient;
    private final AuthFeignClient authFeignClient;

    public FeignClientService(UserFeignClient userFeignClient, CartFeignClient cartFeignClient, ProductFeignClient productFeignClient, AuthFeignClient authFeignClient) {
        this.userFeignClient = userFeignClient;
        this.cartFeignClient = cartFeignClient;
        this.productFeignClient = productFeignClient;
        this.authFeignClient = authFeignClient;
    }

    public UserDto getUserDtoByAuthId(Long authId){
        return userFeignClient.getUserByAuthId(authId).getBody();
    }

    public CartDto getCartByAuthId(Long authId){
        return cartFeignClient.getCartByUserId(authId).getBody();
    }

    public String getProductNameByProductId(String productId){
        return productFeignClient.getProductById(productId).getBody().getName();
    }

    public AuthDto getAuthDtoByAuthId(Long authId){
        return authFeignClient.getInformationById(authId).getBody();
    }
}
