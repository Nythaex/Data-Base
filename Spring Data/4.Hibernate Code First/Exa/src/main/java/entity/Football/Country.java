package entity.Football;

import entity.BasicTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country  {
    private String id;
    private String name;
    private Set<Continent> continents=new HashSet<>();
    private Set<Town> towns=new HashSet<>();

    public Country() {
    }

    public Country(String id, String name, Set<Continent> continents, Set<Town> towns) {
        this.id = id;
        this.name = name;
        this.continents = continents;
        this.towns = towns;
    }

    @Id
    @Column(columnDefinition = "VARCHAR(3)")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    public Set<Continent> getContinents() {
        return continents;
    }

    public void setContinents(Set<Continent> continents) {
        this.continents = continents;
    }

    @OneToMany(mappedBy = "country")
    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }
}
