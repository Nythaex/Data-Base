package com.example.bonusexa.models.dto.printDtos.ex2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrintCarsFromMakeRoot {
    @XmlElement(name = "car")
    private List<CarDto> cars;

    public PrintCarsFromMakeRoot(List<CarDto> cars) {
        this.cars = cars;
    }

    public PrintCarsFromMakeRoot() {
    }

    public List<CarDto> getCars() {
        return cars;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }
}
