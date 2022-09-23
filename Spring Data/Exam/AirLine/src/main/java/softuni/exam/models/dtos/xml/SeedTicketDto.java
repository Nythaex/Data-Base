package softuni.exam.models.dtos.xml;

import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Town;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class SeedTicketDto {

    @XmlElement(name = "serial-number")
    private String serialNumber;
    @XmlElement(name = "price")
    private BigDecimal price;
    @XmlElement(name = "take-off")
    private String takeoff;
    @XmlElement(name = "from-town")
    private SeedTownNameDto fromTown;
    @XmlElement(name = "to-town")
    private SeedTownNameDto toTown;
    @XmlElement(name = "passenger")
    private SeedPassengerEmailDto passenger;
    @XmlElement(name = "plane")
    private SeedPlaneRegisterNumberDto plane;

    public SeedTicketDto() {

    }


    @Size(min = 2)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    @Positive
    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTakeoff() {
        return takeoff;
    }

    public void setTakeoff(String takeoff) {
        this.takeoff = takeoff;
    }


    public SeedTownNameDto getToTown() {
        return toTown;
    }

    public void setToTown(SeedTownNameDto toTown) {
        this.toTown = toTown;
    }

    public SeedTownNameDto getFromTown() {
        return fromTown;
    }

    public void setFromTown(SeedTownNameDto fromTown) {
        this.fromTown = fromTown;
    }


    public SeedPlaneRegisterNumberDto getPlane() {
        return plane;
    }

    public void setPlane(SeedPlaneRegisterNumberDto plane) {
        this.plane = plane;
    }

    public SeedPassengerEmailDto getPassenger() {
        return passenger;
    }

    public void setPassenger(SeedPassengerEmailDto passenger) {
        this.passenger = passenger;
    }
}
