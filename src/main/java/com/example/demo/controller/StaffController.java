package com.example.demo.controller;

import com.example.demo.dto.StaffDto;
import com.example.demo.dto.UpdateBasketRequestDto;
import com.example.demo.entity.BasketEntity;
import com.example.demo.entity.StaffEntity;
import com.example.demo.service.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/staff")
public class StaffController {
    StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("")
    public StaffEntity addProduct(@RequestBody StaffDto request) {
        return staffService.add(request);
    }

    @GetMapping("")
    public List<StaffEntity> getStaffs(){
        return staffService.fetchAll();
    }

    @GetMapping("{id}")
    public StaffEntity getStaffById(@PathVariable("id") Long id){
        return staffService.fetchById(id);
    }
}
