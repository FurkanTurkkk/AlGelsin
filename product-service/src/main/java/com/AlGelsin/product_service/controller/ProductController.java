package com.AlGelsin.product_service.controller;

import com.AlGelsin.product_service.dto.CreateProductRequestDto;
import com.AlGelsin.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.AlGelsin.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@Valid @RequestBody CreateProductRequestDto request){
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/product-price/{product-id}")
    public BigDecimal getProductPrice(@PathVariable("product-id") String productId){
        return productService.getProductPrice(productId);
    }

    @GetMapping("/find")
    public ResponseEntity<ProductDto> getProductById(@RequestParam String productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/product-list/{category-id}/get")
    public ResponseEntity<List<ProductDto>> getProductListByCategoryId(@PathVariable("category-id")Long categoryId){
        return ResponseEntity.ok(productService.getProductListByCategoryId(categoryId));
    }

}
