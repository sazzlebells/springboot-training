package com.example.demo.service;

import com.example.demo.dto.StaffDto;
import com.example.demo.dto.UpdateBasketRequestDto;
import com.example.demo.entity.BasketEntity;
import com.example.demo.entity.StaffEntity;
import com.example.demo.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    StaffRepository staffRepository;

    public StaffService(StaffRepository repository) {
        this.staffRepository = repository;
    }

    public StaffEntity add(StaffDto request) {
        StaffEntity staff = new StaffEntity();
        staff.setName(request.getName());
        staff.setBasket(new BasketEntity());

        return staffRepository.save(staff);
    }

    public List<StaffEntity> fetchAll(){
        return (List<StaffEntity>) staffRepository.findAll();
    }

    public StaffEntity fetchById(Long id) {
        return staffRepository.findById(id).orElse(new StaffEntity());
    }


}
