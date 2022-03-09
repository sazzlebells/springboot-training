package com.example.demo.controller;

import com.example.demo.dto.UpdateBasketRequestDto;
import com.example.demo.entity.BasketEntity;
import com.example.demo.entity.StaffEntity;
import com.example.demo.service.BasketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/basket")
public class BasketController {
    BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("{id}")
    public BasketEntity getBasketById(@PathVariable("id") Long id){
        return basketService.fetchById(id);
    }

    @PutMapping("/basket")
    public BasketEntity addBalance(@RequestBody UpdateBasketRequestDto request){
        return basketService.addBalance(request);
    }
}
