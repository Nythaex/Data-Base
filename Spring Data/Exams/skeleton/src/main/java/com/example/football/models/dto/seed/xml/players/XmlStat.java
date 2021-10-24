package com.example.football.models.dto.seed.xml.players;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlStat {
    @XmlElement(name = "id")
    private Long id;

    public XmlStat(Long id) {
        this.id = id;
    }
    public XmlStat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
