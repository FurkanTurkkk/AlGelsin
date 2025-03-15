package com.AlGelsin.product_service.feignclient;

import com.AlGelsin.product_service.dto.CreateStockRequestDto;
import com.AlGelsin.product_service.dto.UpdateStockRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "stock-service",url = "http://localhost:8085/api/v1/stock")
public interface StockFeignClient {
    @PostMapping("/create")
    public ResponseEntity<String> createStock(@RequestBody CreateStockRequestDto request);

    @GetMapping("/get-stock-by-product-id/{product-id}")
    public ResponseEntity<Integer> getStockInformationByProductId(@PathVariable("product-id") String productId);

    @PutMapping("/update")
    public ResponseEntity<String> updateStock(@RequestBody UpdateStockRequestDto request);
}
