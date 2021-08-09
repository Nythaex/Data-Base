package com.example.exa.model.dto;



import java.util.Set;

public class UserWithProductsDto {

    private String firstName;

    private String lastName;

    private Set<ProductWithBuyer> soldProducts;

    public UserWithProductsDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserWithProductsDto() {
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

    public Set<ProductWithBuyer> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductWithBuyer> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
