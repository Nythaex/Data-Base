package com.example.bonusexa;

import com.example.bonusexa.models.dto.printDtos.CarWithPartsDto;
import com.example.bonusexa.models.dto.printDtos.CustomerByBirthDateDto;
import com.example.bonusexa.services.*;
import com.google.gson.Gson;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class Runner implements ApplicationRunner {
    private final CarService carService;
    private final CustomerService customerService;
    private final PartService partService;
    private final SaleService saleService;
    private final SupplierService supplierService;
    private final Gson gson;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Runner(CarService carService, CustomerService customerService, PartService partService, SaleService saleService, SupplierService supplierService, Gson gson) {
        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.supplierService = supplierService;
        this.gson = gson;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seedData();
        System.out.print("Ex:");
        int exa = Integer.parseInt(reader.readLine());
        switch (exa) {
            case 1:
                List<CustomerByBirthDateDto> allCustomersByBirthDate = customerService.getAllCustomersByBirthDate();
                System.out.println(gson.toJson(allCustomersByBirthDate));

                break;
            case 2:
                System.out.println(gson.toJson(carService.getAllCarsByMake("Toyota")));
                break;
            case 3:
                System.out.println(gson.toJson(supplierService.getAllSuppliersThatAreNotFromAbroad()));
                break;
            case 4:
                List<CarWithPartsDto> allCars = carService.getAllCars();
                System.out.println(gson.toJson(carService.getAllCars()));
                break;
            case 5:
                System.out.println(gson.toJson(customerService.getAllCustomersAndCarsBoughtWithTotalPrice()));
                break;
            case 6:
                System.out.println(gson.toJson(saleService.getAllSalesWithInformation()));
                break;

        }


    }

    private void seedData() {
        try {
            supplierService.seed();
            partService.seed();
            carService.seed();
            customerService.seed();
            saleService.seed();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
