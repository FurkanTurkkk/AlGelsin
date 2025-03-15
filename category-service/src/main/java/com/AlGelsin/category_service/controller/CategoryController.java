package com.AlGelsin.category_service.controller;

import com.AlGelsin.category_service.dto.CreateCategoryRequestDto;
import com.AlGelsin.category_service.dto.UpdateCategoryRequestDto;
import com.AlGelsin.category_service.service.CategoryService;
import jakarta.validation.Valid;
import org.AlGelsin.CategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CreateCategoryRequestDto request){
        categoryService.createCategory(request);
        return ResponseEntity.ok("Category created successfully");
    }

    @GetMapping("/get/product-list/{category-id}")
    public ResponseEntity<CategoryDto> getCategoryWithProducts(@PathVariable("category-id") Long categoryId){
        return ResponseEntity.ok(categoryService.getCategoryWithProducts(categoryId));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestBody UpdateCategoryRequestDto request){
        categoryService.updateCategory(request);
        return ResponseEntity.ok("Category updated successfully");
    }
}
