package softuni.exam.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Offer extends BasicEntity{
    private Double price;
    private String description;
    private Boolean hasGoldStatus;
    private LocalDateTime addedOn;
    private Car car;
    private Seller seller;
    private Set<Picture> pictures;

    public Offer() {

    }
    public Offer(Double price, String description, Boolean hasGoldStatus, LocalDateTime addedOn) {
        this.price = price;
        this.description = description;
        this.hasGoldStatus = hasGoldStatus;
        this.addedOn = addedOn;
    }

    @Column(precision = 19, scale = 2)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(columnDefinition = "TEXT")
    @Size(min = 5)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "has_gold_status")
    public Boolean getHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(Boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }


    @ManyToOne
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @ManyToMany
    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }
}
