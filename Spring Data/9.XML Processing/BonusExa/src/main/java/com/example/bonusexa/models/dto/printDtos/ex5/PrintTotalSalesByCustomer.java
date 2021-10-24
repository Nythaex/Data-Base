package com.example.bonusexa.models.dto.printDtos.ex5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrintTotalSalesByCustomer {
    @XmlElement(name = "customer")
    private List<CustomerCarsDtos> customers;

    public PrintTotalSalesByCustomer(List<CustomerCarsDtos> customers) {
        this.customers = customers;
    }

    public PrintTotalSalesByCustomer() {
    }

    public List<CustomerCarsDtos> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerCarsDtos> customers) {
        this.customers = customers;
    }
}
