package com.example.football.models.dto.seed.json;

import com.google.gson.annotations.Expose;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class SeedTownDto {
    @Expose
    private String name;
    @Expose
    private Integer population;
    @Expose
    private String travelGuide;

    public SeedTownDto() {
    }


    public SeedTownDto(String name, Integer population, String travelGuide) {
        this.name = name;
        this.population = population;
        this.travelGuide = travelGuide;
    }


    @Size(min = 2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Min(1)
    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }


    @Size(min = 10)
    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
