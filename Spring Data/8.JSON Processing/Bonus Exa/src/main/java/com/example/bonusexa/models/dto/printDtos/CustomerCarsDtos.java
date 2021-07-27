package com.example.bonusexa.models.dto.printDtos;

import com.google.gson.annotations.Expose;

public class CustomerCarsDtos
{
    @Expose
    private String fullName;
    @Expose
    private Long boughtCars;
    @Expose
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
