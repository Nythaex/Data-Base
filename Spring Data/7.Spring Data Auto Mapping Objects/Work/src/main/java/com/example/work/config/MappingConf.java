package com.example.work.config;

import com.example.work.models.Employee;
import com.example.work.models.dtos.EmployeeDto;
import com.example.work.models.dtos.ManagerDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public  class MappingConf {

    @Bean
    public  ModelMapper employeeDtoMapper() {
        ModelMapper mapper = new ModelMapper();
          mapper.addMappings(new PropertyMap<Employee, EmployeeDto>() {
              @Override
              protected void configure() {
                 map().setSalary(source.getIncome());
              }
          });
//          mapper.addMappings(new PropertyMap<Employee, ManagerDto>() {
//              @Override
//              protected void configure() {
//                  map().setEmployees(source.getEmployees().stream().map(s->mapper.map(s,EmployeeDto.class)).collect(Collectors.toList()));
//              }
//          });



        return mapper;
    }



}


