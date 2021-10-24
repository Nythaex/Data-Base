package com.example.bonusexa.services;

import com.example.bonusexa.models.dto.printDtos.ex6.PrintSalesWithDiscount;
import com.example.bonusexa.models.dto.printDtos.ex6.SalesAndCustomersAndCarsDto;

import java.util.List;

public interface SaleService {
    void seed();

    PrintSalesWithDiscount getAllSalesWithInformation();
}
