package com.example.exa.model.dto.ex4;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithAgeAndProductsDto {

    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlAttribute(name = "age")
    private int age;


    @XmlElement(name = "sold-products")
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
