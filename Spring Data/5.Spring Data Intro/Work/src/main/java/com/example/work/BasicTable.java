package com.example.work;

import javax.persistence.*;

@MappedSuperclass
public abstract class BasicTable {
    private Long id;

    public BasicTable() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
