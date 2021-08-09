package com.example.bonusexa.services;

import com.example.bonusexa.models.dto.printDtos.SalesAndCustomersAndCarsDto;

import java.util.List;

public interface SaleService {
    void seed();

    List<SalesAndCustomersAndCarsDto> getAllSalesWithInformation();
}
