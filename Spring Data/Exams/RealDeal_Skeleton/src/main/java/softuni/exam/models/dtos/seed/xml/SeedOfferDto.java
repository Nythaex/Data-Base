package softuni.exam.models.dtos.seed.xml;

import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Seller;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class SeedOfferDto {
    @XmlElement(name = "description")
    private String description;
    @XmlElement(name = "price")
    private Double price;
    @XmlElement(name = "added-on")
    private String addedOn;
    @XmlElement(name = "has-gold-status")
    private Boolean hasGoldStatus;
    @XmlElement(name = "car")
    private CarDto car;
    @XmlElement(name = "seller")
    private SellerDto seller;

    public SeedOfferDto(String description, Double price, String addedOn, Boolean hasGoldStatus, CarDto car, SellerDto seller) {
        this.description = description;
        this.price = price;
        this.addedOn = addedOn;
        this.hasGoldStatus = hasGoldStatus;
        this.car = car;
        this.seller = seller;
    }

    public SeedOfferDto() {
    }

    @Size(min = 5)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Positive
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public Boolean getHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(Boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public SellerDto getSeller() {
        return seller;
    }

    public void setSeller(SellerDto seller) {
        this.seller = seller;
    }
}
