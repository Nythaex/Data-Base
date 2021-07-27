package com.example.bonusexa.services.impl;

import com.example.bonusexa.models.Car;
import com.example.bonusexa.models.Customer;
import com.example.bonusexa.models.Part;
import com.example.bonusexa.models.Sale;
import com.example.bonusexa.models.dto.printDtos.CustomerByBirthDateDto;
import com.example.bonusexa.models.dto.printDtos.CustomerCarsDtos;
import com.example.bonusexa.models.dto.printDtos.SalesDto;
import com.example.bonusexa.models.dto.seedDtos.SeedCarDto;
import com.example.bonusexa.models.dto.seedDtos.SeedCustomerDto;
import com.example.bonusexa.repos.CustomerRepository;
import com.example.bonusexa.services.CustomerService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.bonusexa.constants.Constants.FILES_PATH;

@Service
public class CustomerServiceImpl implements CustomerService {

   private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final String fileName = "customers.json";
    private final Validator validator;

    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ModelMapper mapper, Validator validator) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void seed() throws IOException {
        if (customerRepository.count()>0){
            return;
        }
        String items = Files.readString(Path.of(FILES_PATH + fileName));
        SeedCustomerDto[] customers = gson.fromJson(items, SeedCustomerDto[].class);

        Arrays.stream(customers).filter(s->validator.validate(s).isEmpty()).forEach(s->{
            Customer customer=mapper.map(s,Customer.class);
            customer.setYongDriver(s.getYoungDriver());

            LocalDateTime parse = LocalDateTime.parse(s.getBirthDate());
            customer.setBirth(parse);

            customerRepository.save(customer);
        });


    }

    @Override
    public List<CustomerByBirthDateDto> getAllCustomersByBirthDate() {
        List<CustomerByBirthDateDto> customerByBirthDateDtoStream = customerRepository.getCustomersByOrderByBirthAsc().stream().map(s -> {
            CustomerByBirthDateDto customer = mapper.map(s, CustomerByBirthDateDto.class);
            customer.setBirthDate(String.valueOf(s.getBirth()));

            List<SalesDto> collect = s.getSales().stream().map(sale -> {
                SalesDto salesDto = mapper.map(sale, SalesDto.class);
                return salesDto;
            }).collect(Collectors.toList());

            customer.setSales(collect);
//
            return customer;
        }).collect(Collectors.toList());
        return customerByBirthDateDtoStream;
    }

    @Override
    public List<CustomerCarsDtos> getAllCustomersAndCarsBoughtWithTotalPrice() {

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
        }).sorted((s1,s2)->s2.getBoughtCars().compareTo(s1.getBoughtCars()))
                .sorted((s1,s2)->s2.getTotalSum().compareTo(s1.getTotalSum())).collect(Collectors.toList());


        return collect;
    }
}
