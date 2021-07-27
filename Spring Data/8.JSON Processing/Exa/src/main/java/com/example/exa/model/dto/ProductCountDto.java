package com.example.exa.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductCountDto {
    @Expose
    @SerializedName(value = "count")
    private int productCount;
    @Expose
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
