package com.example.exa.service.interf;

import com.example.exa.model.dto.AllUserWitSoldItemsDto;
import com.example.exa.model.dto.UserWithProductsDto;
import com.example.exa.model.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUsers() throws IOException;

    List<UserWithProductsDto> getAllUsersWithAtLeastOneProductSold();

   AllUserWitSoldItemsDto getAllUsersWithAtLeastOneProductSoldByCount();
}
