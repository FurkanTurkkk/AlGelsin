package com.AlGelsin.payment_service.feignclient;

import org.AlGelsin.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service",url = "http://localhost:8083/api/v1/product")
public interface ProductFeignClient {

    @GetMapping("/find")
    public ResponseEntity<ProductDto> getProductById(@RequestParam String productId);
}
