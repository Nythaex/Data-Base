package com.example.bonusexa.models.dto.seedDtos;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class SeedPartDto {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "price")
    private Double price;
    @XmlAttribute(name = "quantity")
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
