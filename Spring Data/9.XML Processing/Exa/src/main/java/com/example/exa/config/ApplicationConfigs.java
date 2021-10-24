package com.example.exa.config;

import org.modelmapper.ModelMapper;
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
