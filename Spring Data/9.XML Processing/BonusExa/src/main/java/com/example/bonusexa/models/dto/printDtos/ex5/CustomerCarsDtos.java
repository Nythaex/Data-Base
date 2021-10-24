package com.example.bonusexa.models.dto.printDtos.ex5;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerCarsDtos
{
    @XmlAttribute(name = "full-name")
    private String fullName;
    @XmlAttribute(name = "bought-cars")
    private Long boughtCars;
    @XmlAttribute(name = "spent-money")
    private Double totalSum;

    public CustomerCarsDtos(String fullName, Long boughtCars, Double totalSum) {
        this.fullName = fullName;
        this.boughtCars = boughtCars;
        this.totalSum = totalSum;
    }

    public CustomerCarsDtos() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Long boughtCars) {
        this.boughtCars = boughtCars;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }
}
