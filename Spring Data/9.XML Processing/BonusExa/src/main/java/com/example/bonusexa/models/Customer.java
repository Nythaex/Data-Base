package com.example.bonusexa.models;


import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends BasicEntity{

    private String name;
    private LocalDateTime birth;
    private Boolean isYoungDriver;
    private Set<Sale> sales;

    public Customer(String name, LocalDateTime birth, Boolean isYongDriver) {
        this.name = name;
        this.birth = birth;
        this.isYoungDriver = isYongDriver;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "birth_date")
    public LocalDateTime getBirth() {
        return birth;
    }

    public void setBirth(LocalDateTime birth) {
        this.birth = birth;
    }

    @Column(name = "is_young_driver")
    public Boolean getYongDriver() {
        return isYoungDriver;
    }

    public void setYongDriver(Boolean yongDriver) {
        isYoungDriver = yongDriver;
    }

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
