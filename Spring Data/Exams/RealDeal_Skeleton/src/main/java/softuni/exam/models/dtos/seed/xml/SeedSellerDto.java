package softuni.exam.models.dtos.seed.xml;

import org.springframework.lang.Nullable;
import softuni.exam.models.entities.Rating;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SeedSellerDto {

    @XmlElement(name = "first-name")
    private String firstName;
    @XmlElement(name = "last-name")
    private String lastName;
    @XmlElement(name = "email")
    private String email;
    @XmlElement(name = "rating")
    private Rating rating;
    @XmlElement(name = "town")
    private String town;

    public SeedSellerDto(String firstName, String lastName, String email,Rating rating, String town) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rating = rating;
        this.town = town;
    }

    public SeedSellerDto() {
    }


    @Size(min = 3, max = 19)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min = 3, max = 19)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

     @NotNull
    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }


    @NotBlank
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
