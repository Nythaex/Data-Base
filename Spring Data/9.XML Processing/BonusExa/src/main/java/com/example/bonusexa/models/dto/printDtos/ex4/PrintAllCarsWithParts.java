package com.example.bonusexa.models.dto.printDtos.ex4;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrintAllCarsWithParts {
    @XmlElement(name = "car")
    private List<CarWithPartsDto> cars;

    public PrintAllCarsWithParts(List<CarWithPartsDto> cars) {
        this.cars = cars;
    }

    public PrintAllCarsWithParts() {
    }

    public List<CarWithPartsDto> getCars() {
        return cars;
    }

    public void setCars(List<CarWithPartsDto> cars) {
        this.cars = cars;
    }
}
