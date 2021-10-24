package com.example.exa.model.dto;



import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SeedCategoryDto {

    @XmlElement(name = "name")
    private String name;

    public SeedCategoryDto(String name) {
        this.name = name;
    }

    public SeedCategoryDto() {
    }

    @Size(min = 3,max = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
