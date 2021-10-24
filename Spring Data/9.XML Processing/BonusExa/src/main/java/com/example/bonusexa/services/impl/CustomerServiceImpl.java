package com.example.bonusexa.services.impl;

import com.example.bonusexa.models.Customer;
import com.example.bonusexa.models.Part;
import com.example.bonusexa.models.Sale;
import com.example.bonusexa.models.dto.printDtos.ex1.CustomerByBirthDateDto;
import com.example.bonusexa.models.dto.printDtos.ex5.CustomerCarsDtos;
import com.example.bonusexa.models.dto.printDtos.ex1.PrintCustomersRoot;
import com.example.bonusexa.models.dto.printDtos.ex5.PrintTotalSalesByCustomer;
import com.example.bonusexa.models.dto.seedDtos.SeedCustomersRoot;
import com.example.bonusexa.repos.CustomerRepository;
import com.example.bonusexa.services.CustomerService;
import com.example.bonusexa.ustils.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final String fileName = "customers.json";
    private final Validator validator;
    private String FILE = "customers.xml";
    public String PATH = "src/main/resources/09. XML-Processing-Exercises/";
    private final XmlParser xmlParser;

    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ModelMapper mapper, Validator validator, XmlParser xmlParser) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validator = validator;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seed() throws IOException, JAXBException {
        if (customerRepository.count() > 0) {
            return;
        }
        SeedCustomersRoot seedCustomersRoot = xmlParser.fromFile(PATH + FILE, SeedCustomersRoot.class);


        seedCustomersRoot.getCustomers().stream().filter(s -> validator.validate(s).isEmpty()).forEach(s -> {
            Customer customer = mapper.map(s, Customer.class);
            customer.setYongDriver(s.getYoungDriver());

            LocalDateTime parse = LocalDateTime.parse(s.getBirthDate());
            customer.setBirth(parse);

            customerRepository.save(customer);
        });


    }

    @Override
    public PrintCustomersRoot getAllCustomersByBirthDate() {

        List<CustomerByBirthDateDto> customerByBirthDateDtoStream = customerRepository.getCustomersByOrderByBirthAsc().stream().map(s -> {
                    CustomerByBirthDateDto customer = mapper.map(s, CustomerByBirthDateDto.class);
                    customer.setBirthDate(String.valueOf(s.getBirth()));

//            List<SalesDto> collect = s.getSales().stream().map(sale -> {
//                SalesDto salesDto = mapper.map(sale, SalesDto.class);
//                return salesDto;
//            }).collect(Collectors.toList());
//
//            customer.setSales(collect);
//
                    return customer;
                }).sorted((s1,s2)->s1.getYoungDriver().compareTo(s2.getYoungDriver()))
                .sorted((s1,s2)->s1.getBirthDate().compareTo(s2.getBirthDate()))
                .collect(Collectors.toList());
        return new PrintCustomersRoot(customerByBirthDateDtoStream);
    }

    @Override
    public PrintTotalSalesByCustomer getAllCustomersAndCarsBoughtWithTotalPrice() {

        List<CustomerCarsDtos> collect = customerRepository.getCustomersCarsAndTotalPrice().stream().map(customer -> {
                    CustomerCarsDtos customerCarsDtos = new CustomerCarsDtos();
                    customerCarsDtos.setFullName(customer.getName());
                    customerCarsDtos.setBoughtCars((long) customer.getSales().size());
                    Double totalPrice = 0d;
                    for (Sale sale : customer.getSales()) {
                        for (Part part : sale.getCar().getParts()) {
                            totalPrice += part.getPrice();
                        }
                    }

                    customerCarsDtos.setTotalSum(Math.round(totalPrice * 100.0) / 100.0);
                    return customerCarsDtos;
                }).sorted((s1, s2) -> s2.getBoughtCars().compareTo(s1.getBoughtCars()))
                .sorted((s1, s2) -> s2.getTotalSum().compareTo(s1.getTotalSum())).collect(Collectors.toList());


        return new PrintTotalSalesByCustomer(collect);
    }
}
