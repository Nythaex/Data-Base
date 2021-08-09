package com.example.exa.model.dto.ex4;


import com.example.exa.model.dto.ex4.ProductNameAndPrice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProductCountDto {

    @XmlAttribute(name = "count")
    private int productCount;

    @XmlElement(name = "product")
    private List<ProductNameAndPrice> products;

    public int getProductCount() {
        return products.size();
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public List<ProductNameAndPrice> getProducts() {
        return products;
    }

    public void setProducts(List<ProductNameAndPrice> products) {
        this.products = products;
    }
}
