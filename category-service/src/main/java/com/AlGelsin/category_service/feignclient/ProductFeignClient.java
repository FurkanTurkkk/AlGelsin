package com.AlGelsin.category_service.feignclient;

import org.AlGelsin.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service",url = "http://localhost:8083/api/v1/product")
public interface ProductFeignClient {

    @GetMapping("/product-list/{category-id}/get")
    public ResponseEntity<List<ProductDto>> getProductListByCategoryId(@PathVariable("category-id")Long categoryId);
}
