package com.example.bonusexa.models.dto.seedDtos;

import com.google.gson.annotations.Expose;

public class SeedPartDto {
    @Expose
    private String name;
    @Expose
    private Double price;
    @Expose
    private int quantity;

    public SeedPartDto(String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public SeedPartDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
