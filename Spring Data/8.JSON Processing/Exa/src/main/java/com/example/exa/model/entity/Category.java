package com.example.exa.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BasicEntity{
    private String name;
    private Set<Product> products=new HashSet<>();

    public Category(String name) {
        this.name = name;
    }
    public Category() {
    }

    @Column(nullable = false,length = 15,unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "categories",fetch = FetchType.EAGER)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return getName().equals(category.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
