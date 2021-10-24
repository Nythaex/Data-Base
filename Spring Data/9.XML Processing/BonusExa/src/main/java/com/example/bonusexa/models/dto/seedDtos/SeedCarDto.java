package com.example.bonusexa.models.dto.seedDtos;



import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SeedCarDto {

    @XmlElement(name = "make")
     private String make;
    @XmlElement(name = "model")
     private String model;
    @XmlElement(name = "travelled-distance")
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
