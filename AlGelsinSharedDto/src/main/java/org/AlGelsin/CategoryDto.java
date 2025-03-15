package org.AlGelsin;

import java.util.List;

public class CategoryDto {

    private Long id;
    private String name;
    private List<ProductDto> productList;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name, List<ProductDto> productList) {
        this.id = id;
        this.name = name;
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ProductDto> getProductList() {
        return productList;
    }
}
