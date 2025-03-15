package com.AlGelsin.product_service.controller;

import com.AlGelsin.product_service.dto.CreateProductRequestDto;
import com.AlGelsin.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.AlGelsin.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/product-list/{category-id}/get")
    public ResponseEntity<List<ProductDto>> getProductListByCategoryId(@PathVariable("category-id")Long categoryId){
        return ResponseEntity.ok(productService.getProductListByCategoryId(categoryId));
    }

}
