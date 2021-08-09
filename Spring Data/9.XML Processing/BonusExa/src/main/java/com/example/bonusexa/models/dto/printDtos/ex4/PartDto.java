package com.example.bonusexa.models.dto.printDtos;

import com.google.gson.annotations.Expose;

public class PartDto {
    @Expose
    private String name;
    @Expose
    private Double price;

    public PartDto(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public PartDto() {
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
}
