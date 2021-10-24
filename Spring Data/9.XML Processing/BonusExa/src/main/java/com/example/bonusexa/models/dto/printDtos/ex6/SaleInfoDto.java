package com.example.bonusexa.models.dto.printDtos.ex6;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SaleInfoDto {
    @XmlElement(name = "car")
    private CarDtoWithoutId carDto;

    @XmlElement(name = "customer-name")
    private String name;

    @XmlElement(name = "discount")
    private double discount;

    @XmlElement(name = "price")
    private Double price;

    @XmlElement(name = "price-with-discount")
    private Double priceWithDiscount;

    public CarDtoWithoutId getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDtoWithoutId carDto) {
        this.carDto = carDto;
    }

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
