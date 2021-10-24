package com.example.bonusexa.models.dto.seedDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedSuppliersRoot {
    @XmlElement(name = "supplier")
    private List<SeedSupplierDto> suppliers;

    public SeedSuppliersRoot(List<SeedSupplierDto> suppliers) {
        this.suppliers = suppliers;
    }

    public SeedSuppliersRoot() {
    }

    public List<SeedSupplierDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SeedSupplierDto> suppliers) {
        this.suppliers = suppliers;
    }
}
