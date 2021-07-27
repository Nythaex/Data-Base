package com.example.springintroexa.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BasicTable{
    private String mame;

    public Category(String mame) {
        this.mame = mame;
    }

    public Category() {
    }

    @Override
    @Column(name = "category_id")
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    public String getMame() {
        return mame;
    }

    public void setMame(String mame) {
        this.mame = mame;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(mame, category.mame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mame);
    }
}
