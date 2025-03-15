package com.AlGelsin.product_service.converter;

import com.AlGelsin.product_service.feignclient.StockFeignClient;
import com.AlGelsin.product_service.model.Product;
import org.AlGelsin.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoConverter {
    private final StockFeignClient stockFeignClient;

    public ProductDtoConverter(StockFeignClient stockFeignClient) {
        this.stockFeignClient = stockFeignClient;
    }

    public ProductDto convert(Product product){
        int quantity = stockFeignClient.getStockInformationByProductId(product.getId()).getBody();
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                quantity,
                product.getDescription()
        );
    }
}
