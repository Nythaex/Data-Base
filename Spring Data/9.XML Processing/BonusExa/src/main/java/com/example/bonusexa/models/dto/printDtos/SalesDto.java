package com.example.bonusexa.models.dto.printDtos;

import com.example.bonusexa.models.Customer;
import com.google.gson.annotations.Expose;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class SalesDto {
    @Expose
    private Double discount;
    @Expose
    private CarDto car;
    @Expose
    private Customer customer;

    public SalesDto(Double discount, CarDto car, Customer customer) {
        this.discount = discount;
        this.car = car;
        this.customer = customer;
    }

    public SalesDto() {
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @OneToOne
    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
