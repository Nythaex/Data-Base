package com.example.bonusexa.models.dto.printDtos.ex1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "customers")
public class PrintCustomersRoot {
    @XmlElement(name = "customer")
    private List<CustomerByBirthDateDto> customers;

    public PrintCustomersRoot(List<CustomerByBirthDateDto> customers) {
        this.customers = customers;
    }

    public PrintCustomersRoot() {
    }

    public List<CustomerByBirthDateDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerByBirthDateDto> customers) {
        this.customers = customers;
    }
}
