package softuni.exam.models.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SeedPassengerEmailDto {
    @XmlElement(name = "email")
    private String email;

    public SeedPassengerEmailDto(String email) {
        this.email = email;
    }
    public SeedPassengerEmailDto() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
