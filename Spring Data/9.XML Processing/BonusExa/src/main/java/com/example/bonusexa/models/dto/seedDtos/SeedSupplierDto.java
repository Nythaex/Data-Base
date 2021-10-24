package com.example.bonusexa.models.dto.seedDtos;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class SeedSupplierDto {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "is-importer")
    private Boolean isImporter;

    public SeedSupplierDto(String name, Boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public SeedSupplierDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
