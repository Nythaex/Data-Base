package com.example.exabonus.models.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Albums extends BasicEntity{
    private String name;
    private String background_color;
    private Boolean is_it_public;
    @ManyToMany
    private Set<Pictures> pictures;

    public Albums(String name, String background_color, Boolean is_it_public) {
        this.name = name;
        this.background_color = background_color;
        this.is_it_public = is_it_public;
    }

    public Albums() {
    }

    public String getName() {
        return name;
    }

    public Albums setName(String name) {
        this.name = name;
        return this;
    }

    public String getBackground_color() {
        return background_color;
    }

    public Albums setBackground_color(String background_color) {
        this.background_color = background_color;
        return this;
    }

    public Boolean getIs_it_public() {
        return is_it_public;
    }

    public Albums setIs_it_public(Boolean is_it_public) {
        this.is_it_public = is_it_public;
        return this;
    }

    public Set<Pictures> getPictures() {
        return pictures;
    }

    public Albums setPictures(Set<Pictures> pictures) {
        this.pictures = pictures;
        return this;
    }
}
