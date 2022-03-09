package com.example.demo.dto;

public class UpdateBasketRequestDto {
    private long id; //this will be staff id
    private long amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
