package com.example.exabonus.services;


import com.example.exabonus.models.service.UserServiceModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface UsersService {
    public void addUser(UserServiceModel userServiceModel);
    public String findAllEmail(String email);
    public void setIsDeleted(LocalDate localDate);
}
