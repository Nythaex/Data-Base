package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "towns")
public class Town extends BasicEntity{
    private String name;
    private Integer population;
    private String travelGuide;

    public Town() {
    }

    public Town(String name, Integer population, String travelGuide) {
        this.name = name;
        this.population = population;
        this.travelGuide = travelGuide;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
    @Column(columnDefinition = "TEXT",name = "travel_guide")
    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
