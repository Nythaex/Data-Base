package com.example.bonusexa.models.dto.printDtos.ex6;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class CarDtoWithoutId {

    @XmlAttribute(name = "make")
    private String make;

    @XmlAttribute(name = "model")
    private String model;

    @XmlAttribute(name = "travelled-distance")
    private Double travelledDistance;


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

    public void setTravelledDistance(double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public CarDtoWithoutId(String make, String model, double travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }



    public CarDtoWithoutId() {
    }

    public void setTravelledDistance(Double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

}
