package com.example.exa.model.dto;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class SeedProductDto {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "price")
    private BigDecimal price;

    public SeedProductDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public SeedProductDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
