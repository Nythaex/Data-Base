package com.example.bonusexa.models.dto.printDtos;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersPrintDto {
    @XmlAttribute(name = "id")
    private Long id;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "parts_count")
    private Integer partsCountDto;

    public SuppliersPrintDto(Long id, String name, Integer partsCountDto) {
        this.id = id;
        this.name = name;
        this.partsCountDto = partsCountDto;
    }

    public SuppliersPrintDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartsCountDto() {
        return partsCountDto;
    }

    public void setPartsCountDto(Integer partsCountDto) {
        this.partsCountDto = partsCountDto;
    }
}
