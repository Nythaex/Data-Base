package com.example.exabonus.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@org.springframework.context.annotation.Configuration
public class Configuration {

    private ModelMapper modelMapper;

    @Bean
     public ModelMapper getMapper(){
            modelMapper=new ModelMapper();

          return modelMapper;
     }

}
