package com.example.exa.config;

import com.example.exa.model.dto.ProductWithSellerDto;
import com.example.exa.model.dto.UserWithProductsDto;
import com.example.exa.model.entity.Product;
import com.example.exa.model.entity.User;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigs {

    @Bean
    public ModelMapper mapper(){
        ModelMapper modelMapper=new ModelMapper();

        return modelMapper;
    }


}
