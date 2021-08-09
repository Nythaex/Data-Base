package com.example.exa.model.dto;

import com.example.exa.model.entity.User;


import java.math.BigDecimal;

public class ProductWithSellerDto {

    private String name;

    private BigDecimal price;

    private String seller;

    public ProductWithSellerDto(String name, BigDecimal price, String seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }

    public ProductWithSellerDto() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
