package com.example.bonusexa.models.dto.seedDtos;

import com.google.gson.annotations.Expose;

import javax.persistence.Entity;

public class SeedCarDto {
    @Expose
     private String make;
    @Expose
     private String model;
    @Expose
     private Double travelledDistance;


    public SeedCarDto(String make, String model, Double travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }

    public SeedCarDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
