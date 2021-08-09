package com.example.bonusexa.models;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "parts")
public class Part extends BasicEntity{
    private String name;
    private Double price;
    private Long quantity;
    private Supplier supplier;
    private Set<Car> cars;

    public Part(String name, Double price, Long quantity, Supplier supplier) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public Part() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @ManyToMany(mappedBy = "parts")
    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Part)) return false;
        Part part = (Part) o;
        return getName().equals(part.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
