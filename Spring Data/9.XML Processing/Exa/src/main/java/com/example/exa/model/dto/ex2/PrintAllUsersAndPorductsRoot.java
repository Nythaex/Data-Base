package com.example.exa.model.dto.ex2;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrintAllUsersAndPorductsRoot {

    @XmlElement(name = "user")
    private List<UserWithProductsDto> users;

    public PrintAllUsersAndPorductsRoot(List<UserWithProductsDto> users) {
        this.users = users;
    }

    public PrintAllUsersAndPorductsRoot() {
    }

    public List<UserWithProductsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithProductsDto> users) {
        this.users = users;
    }
}
