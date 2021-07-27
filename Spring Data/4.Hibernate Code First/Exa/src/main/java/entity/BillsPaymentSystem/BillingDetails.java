package entity.BillsPaymentSystem;

import entity.BasicTable;

import javax.persistence.*;

@Entity
@Table(name = "billing_detail")
@Inheritance(strategy = InheritanceType.JOINED)
public class BillingDetails extends BasicTable {
    private String number;
    private User owner;

    public BillingDetails(String number, User owner) {
        this.number = number;
        this.owner = owner;
    }

    public BillingDetails() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ManyToOne
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
