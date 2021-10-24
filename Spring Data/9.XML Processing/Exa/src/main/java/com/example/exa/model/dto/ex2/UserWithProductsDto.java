package com.example.exa.model.dto.ex2;



import com.example.exa.model.dto.ex2.ProductWithBuyer;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithProductsDto {

    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
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
