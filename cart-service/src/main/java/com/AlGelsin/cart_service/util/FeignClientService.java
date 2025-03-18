package com.AlGelsin.cart_service.util;

import com.AlGelsin.cart_service.feignclient.ProductFeignClient;
import com.AlGelsin.cart_service.feignclient.UserFeignClient;
import org.AlGelsin.ProductDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FeignClientService {
    private final ProductFeignClient productFeignClient;
    private final UserFeignClient userFeignClient;

    public FeignClientService(ProductFeignClient productFeignClient, UserFeignClient userFeignClient) {
        this.productFeignClient = productFeignClient;
        this.userFeignClient = userFeignClient;
    }

    public BigDecimal getProductPrice(String productId){
        ProductDto productDto = productFeignClient.getProductById(productId).getBody();
        return productDto.getPrice();
    }

    public String getUserIdByAuthId(Long authId){
        return userFeignClient.getUserIdByAuthId(authId).getBody();
    }
}
