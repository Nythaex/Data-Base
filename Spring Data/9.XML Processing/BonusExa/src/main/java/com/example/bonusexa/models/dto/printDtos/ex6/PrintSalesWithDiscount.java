package com.example.bonusexa.models.dto.printDtos.ex6;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrintSalesWithDiscount {
    @XmlElement(name = "sale")
    private List<SalesAndCustomersAndCarsDto> sales;

    public PrintSalesWithDiscount(List<SalesAndCustomersAndCarsDto> sales) {
        this.sales = sales;
    }

    public PrintSalesWithDiscount() {
    }

    public List<SalesAndCustomersAndCarsDto> getSales() {
        return sales;
    }

    public void setSales(List<SalesAndCustomersAndCarsDto> sales) {
        this.sales = sales;
    }
}
