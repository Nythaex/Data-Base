package com.example.springmappingexar.models.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User extends BaseTable {
    private String email;
    private String password;
    private String fullName;
    private Set<Game> games;
    private Boolean isAdmin=false;
    private Set<Order> orders;

    public User(String email, String password, String fullName, Set<Game> games) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.games = games;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
    @Column(name = "is_admin")
    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
