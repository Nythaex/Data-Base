package com.example.work.models;

import com.example.work.BasicTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BasicTable {
    private String username;
    private int age;
    private Set<Account> accounts=new HashSet<>();

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public User() {
    }
    @Column (unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
    @OneToMany(cascade = CascadeType.PERSIST)
    public Set<Account> getAccounts() {
        return accounts;
    }

}
