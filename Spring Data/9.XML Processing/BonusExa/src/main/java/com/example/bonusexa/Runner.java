package com.example.bonusexa;

import com.example.bonusexa.models.dto.printDtos.ex1.CustomerByBirthDateDto;
import com.example.bonusexa.models.dto.printDtos.ex1.PrintCustomersRoot;
import com.example.bonusexa.models.dto.printDtos.ex2.PrintCarsFromMakeRoot;
import com.example.bonusexa.models.dto.printDtos.ex3.PrintLocalSuppliersRoot;
import com.example.bonusexa.models.dto.printDtos.ex4.PrintAllCarsWithParts;
import com.example.bonusexa.models.dto.printDtos.ex5.PrintTotalSalesByCustomer;
import com.example.bonusexa.models.dto.printDtos.ex6.PrintSalesWithDiscount;
import com.example.bonusexa.services.*;
import com.example.bonusexa.ustils.XmlParser;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
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
    public  String PATH="src/main/resources/09. XML-Processing-Exercises/out/";
    private final XmlParser xmlParser;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Runner(CarService carService, CustomerService customerService, PartService partService, SaleService saleService, SupplierService supplierService,  XmlParser xmlParser) {
        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.supplierService = supplierService;
        this.xmlParser = xmlParser;

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seedData();
        System.out.print("Ex:");
        int exa = Integer.parseInt(reader.readLine());
        switch (exa) {

            case 1:
                PrintCustomersRoot allCustomersByBirthDate = customerService.getAllCustomersByBirthDate();
                xmlParser.toFile(PATH+"firstOrdered.xml",allCustomersByBirthDate);

                break;
            case 2:
                PrintCarsFromMakeRoot toyotas = carService.getAllCarsByMake("Toyota");
                xmlParser.toFile(PATH+"second.xml",toyotas);

                break;
            case 3:
                PrintLocalSuppliersRoot allSuppliersThatAreNotFromAbroad = supplierService.getAllSuppliersThatAreNotFromAbroad();
                xmlParser.toFile(PATH+"third.xml",allSuppliersThatAreNotFromAbroad);

                break;
            case 4:
                PrintAllCarsWithParts cars =carService.getAllCars();
                xmlParser.toFile(PATH+"four.xml",cars);
                break;
            case 5:
                PrintTotalSalesByCustomer allCustomersAndCarsBoughtWithTotalPrice = customerService.getAllCustomersAndCarsBoughtWithTotalPrice();
                xmlParser.toFile(PATH+"fifth.xml",allCustomersAndCarsBoughtWithTotalPrice);

                break;
            case 6:
                PrintSalesWithDiscount allSalesWithInformation = saleService.getAllSalesWithInformation();
                xmlParser.toFile(PATH+"sixth.xml",allSalesWithInformation);
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
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }


    }
}
