package com.AlGelsin.stock_service.repository;

import com.AlGelsin.stock_service.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock,Long> {
    Optional<Stock> findByProductId(String productId);
}
