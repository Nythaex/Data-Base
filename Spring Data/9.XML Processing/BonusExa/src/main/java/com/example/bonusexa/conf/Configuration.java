package com.example.bonusexa.conf;

import com.example.bonusexa.models.Customer;
import com.example.bonusexa.models.dto.printDtos.ex1.CustomerByBirthDateDto;
import com.example.bonusexa.models.dto.seedDtos.SeedCustomerDto;
import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<SeedCustomerDto, Customer>() {
            @Override
            protected void configure() {
                map().setYongDriver(source.getYoungDriver());
            }
        });
        mapper.addMappings(new PropertyMap<Customer, CustomerByBirthDateDto>() {
            @Override
            protected void configure() {
                map().setYoungDriver(source.getYongDriver());
            }
        });
//


        return mapper;
    }

    @Bean
    public Gson gson() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        return gson;
    }

}

