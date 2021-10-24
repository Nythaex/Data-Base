package com.example.exabonus.services.impl;

import com.example.exabonus.models.entity.Users;
import com.example.exabonus.models.service.UserServiceModel;
import com.example.exabonus.repositories.UserRepository;
import com.example.exabonus.services.UsersService;
import com.example.exabonus.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UsersService {
      private ValidationUtil validationUtil;
      private UserRepository userRepository;
      private ModelMapper modelMapper;

    public UserServiceImpl(ValidationUtil validationUtil, UserRepository userRepository, ModelMapper modelMapper) {
        this.validationUtil = validationUtil;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addUser(UserServiceModel userServiceModel) {
       if (validationUtil.isValid(userServiceModel)){
           userRepository.save(modelMapper.map(userServiceModel, Users.class));
       }
    }

    @Override
    public String findAllEmail(String email) {
        StringBuilder stringBuilder=new StringBuilder();
        List<String> emails=userRepository.findAll().stream().map(Users::getEmail).filter(s -> s.endsWith(email)).toList();
        if (emails.isEmpty()){
            stringBuilder.append("No users found with email domain "+email);
        }else {
            emails.forEach(s-> stringBuilder.append(s).append(System.lineSeparator()));
        }
       return stringBuilder.toString().trim();
    }

    @Override
    public void setIsDeleted(LocalDate localDate) {
        userRepository.setIsDeleted(localDate);
    }
}
