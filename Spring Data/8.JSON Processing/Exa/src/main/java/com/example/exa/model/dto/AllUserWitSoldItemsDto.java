package com.example.exa.model.dto;

import com.example.exa.model.entity.User;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class AllUserWitSoldItemsDto {
    @Expose
    private int count;
    @Expose
    private List<UserWithAgeAndProductsDto> users=new ArrayList<>();

    public AllUserWitSoldItemsDto(int count) {
        this.count = count;
    }

    public AllUserWitSoldItemsDto() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserWithAgeAndProductsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithAgeAndProductsDto> users) {
        this.users = users;
    }
}
