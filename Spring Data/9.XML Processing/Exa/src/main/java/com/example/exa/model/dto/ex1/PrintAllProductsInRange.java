package com.example.exa.model.dto.ex1;

import javax.print.attribute.standard.MediaSize;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrintAllProductsInRange  {
    @XmlElement(name = "product")
    private  List<ProductWithSellerDto> products;

    public List<ProductWithSellerDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWithSellerDto> products) {
        this.products = products;
    }

    public PrintAllProductsInRange(List<ProductWithSellerDto> products) {
        this.products = products;
    }

    public PrintAllProductsInRange() {
    }
}
