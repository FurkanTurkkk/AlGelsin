package com.AlGelsin.product_service.service;

import com.AlGelsin.product_service.converter.ProductDtoConverter;
import com.AlGelsin.product_service.dto.CreateProductRequestDto;
import com.AlGelsin.product_service.dto.CreateStockRequestDto;
import com.AlGelsin.product_service.exception.ProductAlreadyExistException;
import com.AlGelsin.product_service.exception.ProductNotFoundException;
import com.AlGelsin.product_service.feignclient.StockFeignClient;
import com.AlGelsin.product_service.model.Product;
import com.AlGelsin.product_service.repository.ProductRepository;
import org.AlGelsin.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDtoConverter converter;
    private final StockFeignClient stockFeignClient;

    public ProductService(ProductRepository productRepository, ProductDtoConverter converter, StockFeignClient stockFeignClient) {
        this.productRepository = productRepository;
        this.converter = converter;
        this.stockFeignClient = stockFeignClient;
    }

    @Transactional
    public String createProduct(CreateProductRequestDto request) {
        checkByNameForCreateProduct(request.getName());
        Product product = new Product(
                request.getName(),
                request.getCategoryList(),
                request.getPrice(),
                request.getDescription()
        );
        productRepository.save(product);
        stockFeignClient.createStock(new CreateStockRequestDto(product.getId(),request.getQuantity()));
        return "Product created successfully";
    }

    public List<ProductDto> getProductListByCategoryId(Long categoryId) {
        List<Product> productList = productRepository.findByCategoryIdsContaining(categoryId);
        return productList.stream()
                .map(converter::convert)
                .toList();
    }

    public BigDecimal getProductPrice(String productId) {
        Product product=checkByIdForGetProduct(productId);
        return product.getPrice();
    }

    private void checkByNameForCreateProduct(String name) {
        Optional<Product> product = productRepository.findByName(name);
        if(product.isPresent()){
            throw new ProductAlreadyExistException("Product already exist by name : "+name);
        }
    }

    private Product checkByIdForGetProduct(String productId){
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product could not found by id : "+productId);
        }
        return product.get();
    }

    public ProductDto getProductById(String productId) {
        return converter.convert(productRepository.findById(productId).get());
    }
}
