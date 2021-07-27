package entity.HospitalDatabase;

import com.mysql.cj.jdbc.Blob;
import entity.BasicTable;
import entity.BillsPaymentSystem.BillingDetails;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends BasicTable {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Date dateOfBirthDay;
    private String picture;
    private Boolean medicalInsurance;
    private Set<History> history=new HashSet<>();

    @OneToMany(mappedBy = "patient")
    public Set<History> getHistory() {
        return history;
    }

    public void setHistory(Set<History> history) {
        this.history = history;
    }

    @Column(name = "first_name",nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        if (firstName.length()<1){
            throw new Exception();
        }
        this.firstName = firstName;
    }

    @Column(name = "last_name",nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if (lastName.length()<1){
            throw new Exception();
        }
        this.lastName = lastName;
    }
    @Column(name = "address",nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws Exception {
        if (address.length()<1){
            throw new Exception();
        }
        this.address = address;
    }
    @Column(name = "email",nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (email.length()<1){
            throw new Exception();
        }
        this.email = email;
    }
    @Column(name = "birth_day",nullable = false)
    public Date getDateOfBirthDay() throws Exception {
        if (dateOfBirthDay==null){
            throw new Exception();
        }
        return dateOfBirthDay;
    }

    public void setDateOfBirthDay(Date dateOfBirthDay) {
        this.dateOfBirthDay = dateOfBirthDay;
    }
    @Column(name = "picture",columnDefinition = "BLOB",nullable = false)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) throws Exception {
        if (picture.length()<1){
            throw new Exception();
        }
        this.picture = picture;
    }

    @Column(name = "medical_insurance",nullable = false)
    public Boolean getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(Boolean medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public Patient(String firstName, String lastName, String address, String email, Date dateOfBirthDay, String picture, Boolean medicalInsurance) throws Exception {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setEmail(email);
        setDateOfBirthDay(dateOfBirthDay);
        setPicture(picture);
        setMedicalInsurance(medicalInsurance);
    }

    public Patient() {
    }
}
