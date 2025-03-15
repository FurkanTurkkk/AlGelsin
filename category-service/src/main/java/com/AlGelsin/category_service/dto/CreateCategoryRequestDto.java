package com.AlGelsin.category_service.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateCategoryRequestDto {

    @NotBlank(message = "Category must have name")
    private String name;
    private Long parentCategory;

    public CreateCategoryRequestDto() {
    }

    public CreateCategoryRequestDto(String name) {
        this.name = name;
    }

    public CreateCategoryRequestDto(String name, Long parentCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public String getName() {
        return name;
    }

    public Long getParentCategory() {
        return parentCategory;
    }
}
