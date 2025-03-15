package com.AlGelsin.stock_service.service;

import com.AlGelsin.stock_service.dto.CreateStockRequestDto;
import com.AlGelsin.stock_service.dto.UpdateStockRequestDto;
import com.AlGelsin.stock_service.exception.StockInformationNotFoundByProductId;
import com.AlGelsin.stock_service.model.Stock;
import com.AlGelsin.stock_service.repository.StockRepository;
import org.AlGelsin.StockDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public String createStock(CreateStockRequestDto request) {
        Optional<Stock> stock = stockRepository.findByProductId(request.getProductId());
        if(stock.isPresent()){
            updateStock(new UpdateStockRequestDto(request.getProductId(),request.getQuantity()));
        }
        stockRepository.save(new Stock(
                request.getProductId(),
                request.getQuantity()
        ));
        return "Stock information saved successfully";
    }

    public String updateStock(UpdateStockRequestDto request){
        Optional<Stock> stock = stockRepository.findByProductId(request.getProductId());
        if(stock.isEmpty()){
            throw new StockInformationNotFoundByProductId("Stock information could not found for product");
        }
        stock.get().updateQuantity(request.getQuantity());
        stockRepository.save(stock.get());
        return "Stock information updated successfully";
    }

    public int getStockInformationByProductId(String productId) {
        Optional<Stock> stock = stockRepository.findByProductId(productId);
        if(stock.isEmpty()){
            throw new StockInformationNotFoundByProductId("Stock information could not found for product");
        }
        Stock existingStock = stock.get();
        return existingStock.getQuantity();
    }
}
