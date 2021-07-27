package entity.BillsPaymentSystem;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CreditCard extends BillingDetails{
    private String cardType;
    private String expirationMonth;
    private String expirationYear;

    public CreditCard(String number, User owner, String cardType, String expirationMonth, String expirationYear) {
        super(number, owner);
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public CreditCard() {
    }


    @Column(name = "card_type")
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    @Column(name = "get_expiration_month")
    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }
    @Column(name = "get_expiration_year")
    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }
}
