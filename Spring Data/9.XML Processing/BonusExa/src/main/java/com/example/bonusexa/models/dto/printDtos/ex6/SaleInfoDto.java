package com.example.bonusexa.models.dto.printDtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleInfoDto {
    @Expose
    @SerializedName(value = "customerName")
    private String name;

    @Expose
    private double discount;

    @Expose
    private Double price;

    @Expose
    private Double priceWithDiscount;

    public SaleInfoDto(String name, double discount, Double price) {
        this.name = name;
        setDiscount(discount*0.1);
        this.price = price;
        setPriceWithDiscount(this.price-(this.price*this.discount));
    }

    public SaleInfoDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(Double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
    public void calculate(){
        setDiscount(this.discount*0.01);
        setPriceWithDiscount(this.price-(this.price*discount));
    }
}
