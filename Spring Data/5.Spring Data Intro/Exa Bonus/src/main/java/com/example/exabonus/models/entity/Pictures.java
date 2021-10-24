package com.example.exabonus.models.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Pictures extends BasicEntity{

    private String title;
    private String caption;
    private String path;
    @ManyToMany(mappedBy = "pictures")
    private Set<Albums> albums;

    public Pictures(String title, String caption, String path) {
        this.title = title;
        this.caption = caption;
        this.path = path;
    }

    public Pictures() {
    }

    public String getTitle() {
        return title;
    }

    public Pictures setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public Pictures setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Pictures setPath(String path) {
        this.path = path;
        return this;
    }

    public Set<Albums> getAlbums() {
        return albums;
    }

    public Pictures setAlbums(Set<Albums> albums) {
        this.albums = albums;
        return this;
    }
}
