package com.example.bonusexa.models.dto.seedDtos;

import com.google.gson.annotations.Expose;

public class SeedSupplierDto {
    @Expose
    private String name;
    @Expose
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
