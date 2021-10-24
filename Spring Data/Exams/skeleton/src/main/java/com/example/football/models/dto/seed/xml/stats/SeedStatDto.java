package com.example.football.models.dto.seed.xml.stats;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SeedStatDto {
    @XmlElement(name = "passing")
    private Float passing;
    @XmlElement(name = "shooting")
    private Float shooting;
    @XmlElement(name = "endurance")
    private Float endurance;

    public SeedStatDto() {
    }

    public SeedStatDto(Float shooting, Float passing, Float endurance) {
        this.shooting = shooting;
        this.passing = passing;
        this.endurance = endurance;
    }

    @Min(1)
    public Float getShooting() {
        return shooting;
    }

    public void setShooting(Float shooting) {
        this.shooting = shooting;
    }
    @Min(1)
    public Float getPassing() {
        return passing;
    }

    public void setPassing(Float passing) {
        this.passing = passing;
    }
    @Min(1)
    public Float getEndurance() {
        return endurance;
    }

    public void setEndurance(Float endurance) {
        this.endurance = endurance;
    }
}
