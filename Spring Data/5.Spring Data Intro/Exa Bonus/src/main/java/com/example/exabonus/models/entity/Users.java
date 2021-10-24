package com.example.exabonus.models.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Users extends BasicEntity {

    private String username;
    private String password;
    private String email;
    private LocalDate registered_on;
    private LocalDate last_time_logged_in;
    private Integer age;
    private Boolean is_deleted;
    @ManyToOne
    private Towns born_town;
    @ManyToOne
    private Towns currently_living;
    private String first_name;
    @ManyToMany
    private Set<Users> friends;

    public Users(String username, String password, String email, LocalDate registered_on, LocalDate last_time_logged_in, Integer age, Boolean is_deleted, Towns born_town, Towns currently_living, String first_name, String last_name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registered_on = registered_on;
        this.last_time_logged_in = last_time_logged_in;
        this.age = age;
        this.is_deleted = is_deleted;
        this.born_town = born_town;
        this.currently_living = currently_living;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Users() {
    }

@Size
    public String getFullName(){
        return this.first_name+" "+this.last_name;
    }

    public Towns getBorn_town() {
        return born_town;
    }

    public Users setBorn_town(Towns born_town) {
        this.born_town = born_town;
        return this;
    }

    public Towns getCurrently_living() {
        return currently_living;
    }

    public Users setCurrently_living(Towns currently_living) {
        this.currently_living = currently_living;
        return this;
    }

    public String getFirst_name() {
        return first_name;
    }

    public Users setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public Users setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    private String last_name;

    public String getUsername() {
        return username;
    }

    public Users setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Users setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Users setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getRegistered_on() {
        return registered_on;
    }

    public Users setRegistered_on(LocalDate registered_on) {
        this.registered_on = registered_on;
        return this;
    }

    public LocalDate getLast_time_logged_in() {
        return last_time_logged_in;
    }

    public Users setLast_time_logged_in(LocalDate last_time_logged_in) {
        this.last_time_logged_in = last_time_logged_in;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Users setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public Users setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
        return this;
    }

    public Set<Users> getFriends() {
        return friends;
    }

    public Users setFriends(Set<Users> friends) {
        this.friends = friends;
        return this;
    }
}
