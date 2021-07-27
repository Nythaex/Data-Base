package com.example.springmappingexar.services.interfaces;

import com.example.springmappingexar.models.dtos.GameDetailsDto;
import com.example.springmappingexar.models.dtos.LoginUserDto;
import com.example.springmappingexar.models.dtos.RegisterUserDto;
import com.example.springmappingexar.models.entity.Game;
import com.example.springmappingexar.models.entity.User;

import java.util.List;

public interface UserService {
    void registerUser(RegisterUserDto registerUserDto);
    void login(LoginUserDto loginUserDto);
    void logout();

    List<GameDetailsDto> getAllMyGames();
    public User getLoggedIn();
    public List<Game> getOrderCart();

}
