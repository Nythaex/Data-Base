package com.example.bonusexa.models.dto.printDtos.ex6;

import com.example.bonusexa.models.dto.printDtos.ex2.CarDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SalesAndCustomersAndCarsDto {

    @XmlElement(name = "sale")
    private SaleInfoDto saleInfoDto;

    public SalesAndCustomersAndCarsDto( SaleInfoDto saleInfoDto) {

        this.saleInfoDto = saleInfoDto;
    }

    public SalesAndCustomersAndCarsDto() {
    }


    public SaleInfoDto getSaleInfoDto() {
        return saleInfoDto;
    }

    public void setSaleInfoDto(SaleInfoDto saleInfoDto) {
        this.saleInfoDto = saleInfoDto;
    }

}
