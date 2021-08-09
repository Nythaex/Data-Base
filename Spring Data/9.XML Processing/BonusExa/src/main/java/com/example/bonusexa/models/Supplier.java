package com.example.bonusexa.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier extends BasicEntity{
    private String name;
    private Boolean importer;
    private Set<Part> parts;

    public Supplier(String name, Boolean importer) {
        this.name = name;
        this.importer = importer;
    }

    public Supplier() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "is_importer")
    public Boolean getImporter() {
        return importer;
    }

    public void setImporter(Boolean importer) {
        this.importer = importer;
    }

    @OneToMany(mappedBy = "supplier",fetch = FetchType.EAGER)
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
