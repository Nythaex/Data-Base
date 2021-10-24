package com.example.exabonus.models.entity;

import javax.persistence.Entity;

@Entity
public class Towns extends BasicEntity {

    private String name;
    private String country;

    public String getName() {
        return name;
    }

    public Towns() {
    }

    public Towns(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Towns setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Towns setCountry(String country) {
        this.country = country;
        return this;
    }
}
