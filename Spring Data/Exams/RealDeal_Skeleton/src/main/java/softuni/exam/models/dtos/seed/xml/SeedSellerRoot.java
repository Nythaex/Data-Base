package softuni.exam.models.dtos.seed.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedSellerRoot {
    @XmlElement(name = "seller")
    private List<SeedSellerDto> sellers;

    public SeedSellerRoot(List<SeedSellerDto> sellers) {
        this.sellers = sellers;
    }

    public SeedSellerRoot() {
    }

    public List<SeedSellerDto> getSellers() {
        return sellers;
    }

    public void setSellers(List<SeedSellerDto> sellers) {
        this.sellers = sellers;
    }
}
