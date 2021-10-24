package com.example.exa.service.interf;

import com.example.exa.model.dto.ex4.AllUserWitSoldItemsDto;
import com.example.exa.model.dto.ex2.PrintAllUsersAndPorductsRoot;
import com.example.exa.model.dto.ex4.PrintEverything;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface UserService {
    public void seedUsers() throws IOException, JAXBException;
    public AllUserWitSoldItemsDto getAllUsersWithAtLeastOneProductSoldByCount();
    public PrintAllUsersAndPorductsRoot getAllUsersWithAtLeastOneProductSold();
}
