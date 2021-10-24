package com.example.bonusexa.models.dto.seedDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedCarsRoot {
    @XmlElement(name = "car")
    private List<SeedCarDto> cars;

    public SeedCarsRoot(List<SeedCarDto> cars) {
        this.cars = cars;
    }

    public SeedCarsRoot() {
    }

    public List<SeedCarDto> getCars() {
        return cars;
    }

    public void setCars(List<SeedCarDto> cars) {
        this.cars = cars;
    }
}
