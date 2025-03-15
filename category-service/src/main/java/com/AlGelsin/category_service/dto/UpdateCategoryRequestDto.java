package com.AlGelsin.category_service.dto;

public class UpdateCategoryRequestDto {
    private Long categoryId;
    private Long parentCategoryId;

    public UpdateCategoryRequestDto(){}

    public UpdateCategoryRequestDto(Long categoryId,Long parentCategoryId){
        this.categoryId = categoryId;
        this.parentCategoryId = parentCategoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getParentCategoryId(){
        return parentCategoryId;
    }
}
