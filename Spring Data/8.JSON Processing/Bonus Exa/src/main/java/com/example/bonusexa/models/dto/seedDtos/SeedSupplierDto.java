package com.example.bonusexa.models.dto.seedDtos;

public class SeedSupplierDto {
    private String name;
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
