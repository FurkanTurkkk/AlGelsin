package com.AlGelsin.cart_service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock-service",url = "http://localhost:8085/api/v1/stock")
public interface StockFeignClient {

    @GetMapping("/check")
    public void checkStockOfProductForAddToCart(@RequestParam String productId,
                                                @RequestParam int quantity);
}
