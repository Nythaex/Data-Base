package entity.BillsPaymentSystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount extends BillingDetails {
    private String bankName;
    private String SWIFT_Code;

    public BankAccount(String number, User owner, String bankName, String SWIFT_Code) {
        super(number, owner);
        this.bankName = bankName;
        this.SWIFT_Code = SWIFT_Code;
    }

    public BankAccount() {
    }

    @Column (name = "bank_name")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    @Column (name = "SWIFT_code")
    public String getSWIFT_Code() {
        return SWIFT_Code;
    }

    public void setSWIFT_Code(String SWIFT_Code) {
        this.SWIFT_Code = SWIFT_Code;
    }
}
