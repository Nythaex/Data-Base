package softuni.exam.models.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SeedTownNameDto {
    @XmlElement(name = "name")
    private String name;

    public SeedTownNameDto(String name) {
        this.name = name;
    }

    public SeedTownNameDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
