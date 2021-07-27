package com.example.exa.model.dto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductNameAndPrice {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public ProductNameAndPrice(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public ProductNameAndPrice() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
