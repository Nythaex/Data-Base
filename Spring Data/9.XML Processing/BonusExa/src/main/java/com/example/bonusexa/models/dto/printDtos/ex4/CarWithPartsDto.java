package com.example.bonusexa.models.dto.printDtos;

import com.example.bonusexa.models.Part;


import java.util.HashSet;
import java.util.Set;

public class CarWithPartsDto {

    private String make;

    private String model;

    private String travelledDistance;

    private Set<PartDto> parts=new HashSet<>();

    public CarWithPartsDto(String make, String model, String travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }

    public Set<PartDto> getParts() {
        return parts;
    }

    public void setParts(Set<PartDto> parts) {
        this.parts = parts;
    }

    public CarWithPartsDto() {
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

    public String getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(String travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
