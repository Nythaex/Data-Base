package com.example.bonusexa.models.dto.seedDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedPartsRoot {
    @XmlElement(name = "part")
    private List<SeedPartDto> parts;

    public SeedPartsRoot(List<SeedPartDto> parts) {
        this.parts = parts;
    }

    public SeedPartsRoot() {
    }

    public List<SeedPartDto> getParts() {
        return parts;
    }

    public void setParts(List<SeedPartDto> parts) {
        this.parts = parts;
    }
}
