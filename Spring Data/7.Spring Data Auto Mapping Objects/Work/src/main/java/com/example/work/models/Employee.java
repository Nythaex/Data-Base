package com.example.work.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    private Long Id;
    private String firstName;
    private String lastName;
    private BigDecimal income;
    private LocalDate birthDate;
    private String address;
    private Boolean onHoliday;
    private Employee manager;
    private List<Employee> employees;




    public Employee(Long id, String firstName, String lastName, BigDecimal income, LocalDate date, String address) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.income = income;
        this.birthDate = date;
        this.address = address;
    }

    public Employee() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate date) {
        this.birthDate = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getOnHoliday() {
        return onHoliday;
    }

    public void setOnHoliday(Boolean onHoliday) {
        this.onHoliday = onHoliday;
    }

    @ManyToOne
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @OneToMany(mappedBy = "manager",fetch = FetchType.EAGER)
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
