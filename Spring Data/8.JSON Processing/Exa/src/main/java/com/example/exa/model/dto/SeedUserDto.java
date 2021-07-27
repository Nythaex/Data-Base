package com.example.exa.model.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Pattern;

public class SeedUserDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;

    public SeedUserDto(String firstName, String lastName, int age) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public SeedUserDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Pattern(regexp = "[A-z/d]{3,}")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
