package com.example.work.services.interfaces;

import com.example.work.exeptions.UsernameExistsExeption;

import java.math.BigDecimal;


public interface UserService {
    void registration(String username,int age,BigDecimal money) throws Exception, UsernameExistsExeption;


}
