package com.example.bonusexa.services;

import com.example.bonusexa.models.dto.printDtos.ex5.CustomerCarsDtos;
import com.example.bonusexa.models.dto.printDtos.ex1.PrintCustomersRoot;
import com.example.bonusexa.models.dto.printDtos.ex5.PrintTotalSalesByCustomer;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CustomerService {
    void seed() throws IOException, JAXBException;

       PrintCustomersRoot getAllCustomersByBirthDate();

    PrintTotalSalesByCustomer getAllCustomersAndCarsBoughtWithTotalPrice();
}
