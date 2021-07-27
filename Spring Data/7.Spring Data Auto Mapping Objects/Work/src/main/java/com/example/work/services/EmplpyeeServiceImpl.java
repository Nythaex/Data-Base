package com.example.work.services;

import com.example.work.models.dtos.ManagerDto;
import com.example.work.repositories.EmployeeRepository;
import com.example.work.services.interfaces.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmplpyeeServiceImpl implements EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    public EmplpyeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;

        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<ManagerDto> getManagers() {

        List<ManagerDto> managerDtos = new ArrayList<>();


        employeeRepository.findAll().forEach(s -> {
            ManagerDto managerDto = modelMapper.map(s, ManagerDto.class);

//            s.getEmployees().forEach(employee -> {
//                managerDto.getEmployees().add(modelMapper.map(employee, EmployeeDto.class));
//
//            });

            managerDtos.add(managerDto);
        });
        return managerDtos;

        //   return mappingConf.employeeDtoMapper().map(employeeRepository.findAll(), new TypeToken<List<ManagerDto>>() {}.getType());
    }


}
