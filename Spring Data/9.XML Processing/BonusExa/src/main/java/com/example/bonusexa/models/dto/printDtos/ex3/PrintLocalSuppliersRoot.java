package com.example.bonusexa.models.dto.printDtos.ex3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrintLocalSuppliersRoot {
    @XmlElement(name = "suplier")
    private List<SuppliersPrintDto> suppliers;

    public PrintLocalSuppliersRoot(List<SuppliersPrintDto> suppliers) {
        this.suppliers = suppliers;
    }

    public PrintLocalSuppliersRoot() {
    }

    public List<SuppliersPrintDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SuppliersPrintDto> suppliers) {
        this.suppliers = suppliers;
    }
}
