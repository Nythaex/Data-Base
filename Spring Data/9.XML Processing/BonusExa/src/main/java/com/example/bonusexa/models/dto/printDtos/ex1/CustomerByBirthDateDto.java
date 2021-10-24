package com.example.bonusexa.models.dto.printDtos.ex1;

import com.example.bonusexa.models.Sale;
import com.example.bonusexa.models.dto.printDtos.SalesDto;
import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerByBirthDateDto {

    @XmlElement(name = "id")
    private Long id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "birth-date")
    private String birthDate;
    @XmlElement(name = "is-youn-driver")
    private Boolean isYoungDriver;

  //  private List<SalesDto>  sales=new ArrayList<>();

    public CustomerByBirthDateDto(String name, String birthDate, Boolean isYoungDriver) {
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }

    public CustomerByBirthDateDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

//    public List<SalesDto> getSales() {
//        return sales;
//    }
//
//    public void setSales(List<SalesDto> sales) {
//        this.sales = sales;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
