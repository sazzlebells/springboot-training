package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class StaffEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToOne(cascade = {CascadeType.ALL})
    private BasketEntity basket;

    public Long getId(long id) {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BasketEntity getBasket() {
        return basket;
    }

    public void setBasket(BasketEntity basket) {
        this.basket = basket;
    }
}
