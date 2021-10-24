package com.example.football.models.dto.seed.json;

import com.example.football.models.entity.Town;
import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class SeedTeamDto {
    @Expose
    private String name;
    @Expose
    private String stadiumName;
    @Expose
    private Integer fanBase;
    @Expose
    private String history;
    @Expose
    private String townName;

    public SeedTeamDto() {

    }

    public SeedTeamDto(String name, String stadiumName, Integer fanBase, String history) {
        this.name = name;
        this.stadiumName = stadiumName;
        this.fanBase = fanBase;
        this.history = history;
    }


    @Size(min = 3)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 3)
    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    @Min(1000)
    public Integer getFanBase() {
        return fanBase;
    }

    public void setFanBase(Integer fanBase) {
        this.fanBase = fanBase;
    }
    @Size(min = 10)
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
