package softuni.exam.models.dtos.seed.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedOfferRoot {
    @XmlElement(name = "offer")
    private List<SeedOfferDto> offersList;

    public SeedOfferRoot(List<SeedOfferDto> offers) {
        this.offersList = offers;
    }

    public SeedOfferRoot() {
    }

    public List<SeedOfferDto> getOffersList() {
        return offersList;
    }

    public void setOffersList(List<SeedOfferDto> offersList) {
        this.offersList = offersList;
    }
}
