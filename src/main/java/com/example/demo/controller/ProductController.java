package com.example.demo.controller;

import com.example.demo.dto.CommonResponse;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.TransactionDto;
import com.example.demo.dto.UpdateStockRequestDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.BasketService;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<ProductEntity> getProducts(@RequestParam(value = "inStock", defaultValue = "0") boolean inStock,
                                           @RequestParam(value = "priceMax", defaultValue = "300") long priceMax) {
        return productService.fetchAll(inStock, priceMax);
    }

    @GetMapping("{id}")
    public ProductEntity getProduct(@PathVariable("id") long id) {
        return productService.find(id);
    }

    @PostMapping("")
    public ProductEntity addProduct(@RequestBody ProductDto productDto) {
        return productService.add(productDto);
    }

    @PutMapping("/stock")
    public ProductEntity updateStock(@RequestBody UpdateStockRequestDto requestDto) {
        return productService.updateStock(requestDto);
    }

    @DeleteMapping("{id}")
    public CommonResponse deleteProduct(@PathVariable("id") long id) {
        productService.delete(id);
        return new CommonResponse("Successfully delete product");
    }

    @PostMapping("/buy")
    public CommonResponse buyProduct(@RequestBody TransactionDto request){
        return new CommonResponse(productService.buyProduct(request));
    }
}
