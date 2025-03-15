package com.AlGelsin.category_service.converter;

import com.AlGelsin.category_service.feignclient.ProductFeignClient;
import com.AlGelsin.category_service.model.Category;
import org.AlGelsin.CategoryDto;
import org.AlGelsin.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDtoConverter {
    private final ProductFeignClient productFeignClient;

    public CategoryDtoConverter(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    public CategoryDto convert(Category category){
        List<ProductDto> productDtoList = productFeignClient.getProductListByCategoryId(category.getId()).getBody();
        return new CategoryDto(
                category.getId(),
                category.getName(),
                productDtoList
        );
    }
}
