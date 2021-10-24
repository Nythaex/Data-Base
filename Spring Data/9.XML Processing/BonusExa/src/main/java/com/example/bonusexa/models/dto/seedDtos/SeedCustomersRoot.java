package com.example.bonusexa.models.dto.seedDtos;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedCustomersRoot {
    @XmlElement(name = "customer")
    private List<SeedCustomerDto> customers;

    public SeedCustomersRoot(List<SeedCustomerDto> customers) {
        this.customers = customers;
    }

    public SeedCustomersRoot() {
    }

    public List<SeedCustomerDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<SeedCustomerDto> customers) {
        this.customers = customers;
    }
}
