package com.example.bonusexa.models.dto.printDtos.ex4;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class PartDto {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "price")
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
