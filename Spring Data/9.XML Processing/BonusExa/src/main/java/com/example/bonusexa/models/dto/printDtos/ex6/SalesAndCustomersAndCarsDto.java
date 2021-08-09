package com.example.bonusexa.models.dto.printDtos;

import com.example.bonusexa.models.dto.printDtos.ex2.CarDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesAndCustomersAndCarsDto {
    @Expose
    @SerializedName(value = "cars")
    private CarDto carDto;
    @Expose
    private SaleInfoDto saleInfoDto;

    public SalesAndCustomersAndCarsDto(CarDto carDto, SaleInfoDto saleInfoDto) {
        this.carDto = carDto;
        this.saleInfoDto = saleInfoDto;
    }

    public SalesAndCustomersAndCarsDto() {
    }

    public CarDto getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }

    public SaleInfoDto getSaleInfoDto() {
        return saleInfoDto;
    }

    public void setSaleInfoDto(SaleInfoDto saleInfoDto) {
        this.saleInfoDto = saleInfoDto;
    }

}
