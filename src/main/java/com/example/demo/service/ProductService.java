package com.example.demo.service;

import com.example.demo.dto.CommonResponse;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.TransactionDto;
import com.example.demo.dto.UpdateStockRequestDto;
import com.example.demo.entity.BasketEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    Logger logger = LoggerFactory.getLogger(ProductService.class);
    ProductRepository repository;
    BasketService basketService;

    public ProductService(ProductRepository repository, BasketService basketService) {
        this.repository = repository;
        this.basketService = basketService;
    }

    public ProductEntity add(ProductDto request) {
        ProductEntity product = new ProductEntity();
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        return repository.save(product);
    }

    public List<ProductEntity> fetchAll(boolean isAvailableOnly, long priceMax) {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        if (isAvailableOnly) {
            return repository.findByStockGreaterThanAndPriceLessThanEqual(0, priceMax);
        } else {
            //fetch all
//            return (List<ProductEntity>) repository.findAll();
            return fetchPriceLessThanEqual(priceMax);
        }
    }

    public List<ProductEntity> fetchPriceLessThanEqual(long priceMax){
        return (List<ProductEntity>) repository.findByPriceLessThanEqual(priceMax);
    }

    public ProductEntity find(long id) {
        return repository.findById(id).get();
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public ProductEntity updateStock(UpdateStockRequestDto requestDto) {
        ProductEntity product = repository.findById(requestDto.getId()).get();
        product.setStock(requestDto.getStock());

        return repository.save(product);
    }

    public void decreaseStock(Long id, Long qty){
        ProductEntity product = repository.findById(id).get();
        if(product.getStock() >= qty){
            product.setStock(product.getStock() - qty);
        }else {
            //nothing?
        }

        repository.save(product);
    }

    public String buyProduct(TransactionDto request) {
        BasketEntity basket = basketService.fetchById(request.getBasketId());
        ProductEntity product = find(request.getProductId());
        if(basket.getAmount() >= request.getQty() * product.getPrice()){
            decreaseStock(request.getProductId(), request.getQty());
            basketService.decreaseBalance(request.getBasketId(), request.getQty() * product.getPrice());

            return "Successfully bought product";
        }else{
            return "Buy failed! Insufficient balance.";
        }
    }
}
