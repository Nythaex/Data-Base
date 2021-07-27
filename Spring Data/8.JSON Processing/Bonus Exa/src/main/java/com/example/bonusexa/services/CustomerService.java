package com.example.bonusexa.services;

import com.example.bonusexa.models.dto.printDtos.CustomerByBirthDateDto;
import com.example.bonusexa.models.dto.printDtos.CustomerCarsDtos;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    void seed() throws IOException;

   List<CustomerByBirthDateDto> getAllCustomersByBirthDate();

    List<CustomerCarsDtos> getAllCustomersAndCarsBoughtWithTotalPrice();
}
