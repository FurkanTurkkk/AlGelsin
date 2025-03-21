package com.AlGelsin.stock_service.service;

import com.AlGelsin.stock_service.config.StockRabbitConfig;
import com.AlGelsin.stock_service.dto.CreateStockRequestDto;
import com.AlGelsin.stock_service.dto.UpdateStockRequestDto;
import com.AlGelsin.stock_service.exception.StockInformationNotFoundByProductId;
import com.AlGelsin.stock_service.model.Stock;
import com.AlGelsin.stock_service.repository.StockRepository;
import org.AlGelsin.CartDto;
import org.AlGelsin.IncreaseStockEventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {
    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    private final StockRepository stockRepository;
    private final RabbitTemplate rabbitTemplate;

    public StockService(StockRepository stockRepository, RabbitTemplate rabbitTemplate) {
        this.stockRepository = stockRepository;
        this.rabbitTemplate = rabbitTemplate;
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

    public void decreaseQuantity(String productId,int quantity){
        try {
            Stock stock = checkStockInformationByProductId(productId);
            int existingQuantity = stock.getQuantity();
            if(existingQuantity < quantity){
                throw new IllegalArgumentException("Stokta istenilen miktar kadar ürün bulunmamaktadır.");
            }else {
                stock.updateQuantity(existingQuantity- quantity);
                stockRepository.save(stock);
            }

        }catch (StockInformationNotFoundByProductId e){
            logger.info(e.getMessage());
        }

    }

    @RabbitListener(queues = StockRabbitConfig.INCREASE_STOCK_QUEUE)
    public void increaseQuantity(IncreaseStockEventDto stockEventDto){
        stockEventDto.getCartDto().getCartItemDtoList().forEach(
                cartItem -> {
                    String productId = cartItem.getProductId();
                    int quantity = cartItem.getQuantity();
                    Stock stock = stockRepository.findByProductId(productId).get();
                    stock.updateQuantity(stock.getQuantity()+quantity);
                    stockRepository.save(stock);
                }
        );

        rabbitTemplate.convertAndSend(StockRabbitConfig.EXCHANGE_NAME,
                StockRabbitConfig.ROUTING_KEY_CART,
                stockEventDto.getAuthId());
    }

    public int getStockInformationByProductId(String productId) {
        Stock stock = checkStockInformationByProductId(productId);
        return stock.getQuantity();
    }

    private void checkStockOfProductForAddToCart(String productId,int quantity){
        Stock stock = checkStockInformationByProductId(productId);
        int existingQuantity = stock.getQuantity();
        if (quantity > existingQuantity){
            throw new IllegalArgumentException("Stokta istenilen miktar kadar ürün bulunmamaktadır.");
        }

    }

    private Stock checkStockInformationByProductId(String productId){
        Optional<Stock> stock = stockRepository.findByProductId(productId);
        if(stock.isEmpty()){
            throw new StockInformationNotFoundByProductId("Stock information could not found for product");
        }
        return stock.get();
    }
}
