package com.example.bonusexa.services.impl;

import com.example.bonusexa.models.Car;
import com.example.bonusexa.models.Customer;
import com.example.bonusexa.models.Part;
import com.example.bonusexa.models.Sale;
import com.example.bonusexa.models.dto.printDtos.ex6.CarDtoWithoutId;
import com.example.bonusexa.models.dto.printDtos.ex6.PrintSalesWithDiscount;
import com.example.bonusexa.models.dto.printDtos.ex6.SaleInfoDto;
import com.example.bonusexa.models.dto.printDtos.ex6.SalesAndCustomersAndCarsDto;
import com.example.bonusexa.models.dto.printDtos.ex2.CarDto;
import com.example.bonusexa.models.dto.seedDtos.SeedSaleDto;
import com.example.bonusexa.repos.CarRepository;
import com.example.bonusexa.repos.CustomerRepository;
import com.example.bonusexa.repos.SaleRepository;
import com.example.bonusexa.services.SaleService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final Validator validator;
    List<Double> discounts;

    public SaleServiceImpl(CarRepository carRepository, CustomerRepository customerRepository, SaleRepository saleRepository, Gson gson, ModelMapper mapper, Validator validator) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validator = validator;
        discounts = new ArrayList<>();
        discounts.add(0d);
        discounts.add(5d);
        discounts.add(10d);
        discounts.add(15d);
        discounts.add(20d);
        discounts.add(30d);
        discounts.add(40d);
        discounts.add(50d);
    }

    @Override
    public void seed() {
        if (saleRepository.count() > 0) {
            return;
        }
        int salesCount = getRandomNumberUsingInts(50, 500);
        for (int i = 1; i < salesCount; i++) {

            int customerId = getRandomNumberUsingInts(1, (int) customerRepository.count());
            int carId = getRandomNumberUsingInts(1, (int) carRepository.count());
            int discount = getRandomNumberUsingInts(0, discounts.size() - 1);
            Car car = carRepository.findById(Long.parseLong(String.valueOf(carId))).orElse(null);
            Customer customer = customerRepository.findById(Long.parseLong(String.valueOf(customerId))).orElse(null);

            SeedSaleDto seedSaleDto = new SeedSaleDto(discounts.get(discount), car, customer);
            Sale sale = mapper.map(seedSaleDto, Sale.class);
            if (sale.getCustomer().getYongDriver()) {
                sale.setDiscount(sale.getDiscount() + 5);
            }
            saleRepository.save(sale);
        }

    }

    @Override
    public PrintSalesWithDiscount getAllSalesWithInformation() {
        List<SalesAndCustomersAndCarsDto> collect = saleRepository.findAll().stream().map(sale -> {

            SalesAndCustomersAndCarsDto salesAndCustomersAndCarsDto = new SalesAndCustomersAndCarsDto();
            SaleInfoDto saleInfoDto = mapper.map(sale, SaleInfoDto.class);
            saleInfoDto.setCarDto(mapper.map(sale.getCar(),CarDtoWithoutId.class));
            saleInfoDto.setName(sale.getCustomer().getName());
            Double totalPrice = 0d;

            for (Part part : sale.getCar().getParts()) {
                totalPrice += part.getPrice();
            }
            saleInfoDto.setPrice(Math.round(totalPrice * 100.0) / 100.0);
            saleInfoDto.calculate();
            saleInfoDto.setPriceWithDiscount(Math.round(saleInfoDto.getPriceWithDiscount() * 100.0) / 100.0);
            salesAndCustomersAndCarsDto.setSaleInfoDto(saleInfoDto);
            saleInfoDto.setDiscount(Math.round(saleInfoDto.getDiscount() * 100.0) / 100.0);
            return salesAndCustomersAndCarsDto;
        }).collect(Collectors.toList());

        return new PrintSalesWithDiscount(collect);
    }


    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
