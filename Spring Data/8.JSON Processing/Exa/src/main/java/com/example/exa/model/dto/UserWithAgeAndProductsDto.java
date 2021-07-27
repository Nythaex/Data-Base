package com.example.exa.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserWithAgeAndProductsDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;
    @Expose
    @SerializedName(value = "soldProducts")
    private ProductCountDto productCountDtos;

    public UserWithAgeAndProductsDto(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public UserWithAgeAndProductsDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProductCountDto getProductCountDtos() {
        return productCountDtos;
    }

    public void setProductCountDtos(ProductCountDto productCountDtos) {
        this.productCountDtos = productCountDtos;
    }
}
