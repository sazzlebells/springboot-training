package com.example.demo.service;

import com.example.demo.dto.UpdateBasketRequestDto;
import com.example.demo.entity.BasketEntity;
import com.example.demo.entity.StaffEntity;
import com.example.demo.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public BasketEntity fetchById(Long id){
        return basketRepository.findById(id).get();
    }

    public BasketEntity addBalance(UpdateBasketRequestDto request){
        BasketEntity basket = basketRepository.findById(request.getId()).get();
        basket.add(request.getAmount());

        return basketRepository.save(basket);
    }
}
