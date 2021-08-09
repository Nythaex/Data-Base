package com.example.exa.model.dto.ex4;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class AllUserWitSoldItemsDto {

    @XmlAttribute(name = "count")
    private int count;

    @XmlElement(name = "user")
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
