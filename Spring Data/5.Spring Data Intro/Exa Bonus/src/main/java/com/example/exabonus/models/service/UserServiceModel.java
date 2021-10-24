package com.example.exabonus.models.service;


import com.example.exabonus.config.anotation.EmailCustom;
import com.example.exabonus.config.anotation.Password;

import java.time.LocalDate;

public class UserServiceModel {
    private String username;

    @Password
    private String password;
    @EmailCustom()
    private String email;
    private LocalDate registered_on;
    private LocalDate last_time_logged_in;
    private Integer age;
    private Boolean is_deleted;

    public UserServiceModel(String username, String password, String email, LocalDate registered_on, LocalDate last_time_logged_in, Integer age, Boolean is_deleted) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registered_on = registered_on;
        this.last_time_logged_in = last_time_logged_in;
        this.age = age;
        this.is_deleted = is_deleted;
    }

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }


    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }


    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getRegistered_on() {
        return registered_on;
    }

    public UserServiceModel setRegistered_on(LocalDate registered_on) {
        this.registered_on = registered_on;
        return this;
    }

    public LocalDate getLast_time_logged_in() {
        return last_time_logged_in;
    }

    public UserServiceModel setLast_time_logged_in(LocalDate last_time_logged_in) {
        this.last_time_logged_in = last_time_logged_in;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserServiceModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public UserServiceModel setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
        return this;
    }
}
