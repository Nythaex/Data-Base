package softuni.exam.models.dtos.xml;

import org.springframework.data.annotation.AccessType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedPlaneRoot {
    @XmlElement(name = "plane")
    private List<SeedPlaneDto> planesList;

    public SeedPlaneRoot(List<SeedPlaneDto> planes) {
        this.planesList = planes;
    }

    public SeedPlaneRoot() {
    }

    public List<SeedPlaneDto> getPlanes() {
        return planesList;
    }

    public void setPlanes(List<SeedPlaneDto> planes) {
        this.planesList = planes;
    }
}
