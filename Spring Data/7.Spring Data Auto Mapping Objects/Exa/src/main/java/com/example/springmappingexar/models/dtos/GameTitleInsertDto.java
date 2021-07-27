package com.example.springmappingexar.models.dtos;

public class GameTitleInsertDto {
    private String title;

    public GameTitleInsertDto(String title) {
        this.title = title;
    }

    public GameTitleInsertDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
