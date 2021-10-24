package com.example.football.models.dto.seed.xml.stats;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedStatRoot {
    @XmlElement(name = "stat")
    private List<SeedStatDto> statsList;

    public List<SeedStatDto> getStatsList() {
        return statsList;
    }

    public void setStatsList(List<SeedStatDto> statsList) {
        this.statsList = statsList;
    }

    public SeedStatRoot(List<SeedStatDto> statsList) {
        this.statsList = statsList;
    }

    public SeedStatRoot() {
    }
}
