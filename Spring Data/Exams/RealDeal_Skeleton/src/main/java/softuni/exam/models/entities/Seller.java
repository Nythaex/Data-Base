package softuni.exam.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Seller extends BasicEntity{
    private String firstName;
    private String lastName;
    private String email;
    private Rating rating;
    private String town;
    private Set<Offer> offers;

    public Seller() {
    }

    public Seller(String firstName, String lastName, String email, Rating rating, String town) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rating = rating;
        this.town = town;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Enumerated(EnumType.STRING)
    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Column(nullable = false)
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @OneToMany(mappedBy = "seller")
    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
}
