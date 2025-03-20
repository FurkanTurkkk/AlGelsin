package com.AlGelsin.stock_service.controller;

import com.AlGelsin.stock_service.dto.CreateStockRequestDto;
import com.AlGelsin.stock_service.dto.UpdateStockRequestDto;
import com.AlGelsin.stock_service.service.StockService;
import org.AlGelsin.StockDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createStock(@RequestBody CreateStockRequestDto request){
        return ResponseEntity.ok(stockService.createStock(request));
    }

    @GetMapping("/get-stock-by-product-id/{product-id}")
    public ResponseEntity<Integer> getStockInformationByProductId(@PathVariable("product-id") String productId){
        return ResponseEntity.ok(stockService.getStockInformationByProductId(productId));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStock(@RequestBody UpdateStockRequestDto request){
        return ResponseEntity.ok(stockService.updateStock(request));
    }

    @GetMapping("/check")
    public void checkStockOfProductForAddToCart(@RequestParam String productId,
                                                @RequestParam int quantity){
        stockService.decreaseQuantity(productId,quantity);
    }
}
