package com.example.football.models.dto.seed.xml.players;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlTeam {
    @XmlElement(name = "name")
    private String name;

    public XmlTeam(String name) {
        this.name = name;
    }
    public XmlTeam() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
