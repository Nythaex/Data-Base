package com.example.bonusexa.models.dto.seedDtos;

import com.example.bonusexa.models.Car;
import com.example.bonusexa.models.Customer;
import com.google.gson.annotations.Expose;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class SeedSaleDto {
    @Expose
    private Double discount;
    @Expose
    private Car car;
    @Expose
    private Customer customer;

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public SeedSaleDto(Double discount, Car car, Customer customer) {
        this.discount = discount;
        this.car = car;
        this.customer = customer;
    }

    public SeedSaleDto() {
    }
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
