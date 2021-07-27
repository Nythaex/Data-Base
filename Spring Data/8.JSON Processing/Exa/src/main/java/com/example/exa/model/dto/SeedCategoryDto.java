package com.example.exa.model.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;

public class SeedCategoryDto {
    @Expose
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
