package com.AlGelsin.product_service.repository;

import com.AlGelsin.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product,String> {
    List<Product> findByCategoryIdsContaining(Long categoryId);
    Optional<Product> findByName(String name);
}
