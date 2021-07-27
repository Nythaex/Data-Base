package com.example.springmappingexar.services.impl;

import com.example.springmappingexar.models.dtos.GameDetailsDto;
import com.example.springmappingexar.models.dtos.LoginUserDto;
import com.example.springmappingexar.models.dtos.RegisterUserDto;
import com.example.springmappingexar.models.entity.Game;
import com.example.springmappingexar.models.entity.Order;
import com.example.springmappingexar.models.entity.User;
import com.example.springmappingexar.repos.GameRepository;
import com.example.springmappingexar.repos.OrderRepository;
import com.example.springmappingexar.repos.UserRepository;
import com.example.springmappingexar.services.interfaces.UserService;
import com.example.springmappingexar.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private OrderRepository orderRepository;
    private final ValidationUtil validationUtil;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final ModelMapper mapper;
    private User loggedIn = null;
    private List<Game> orderCart;


    public User getLoggedIn(){
        return loggedIn;
    }

    @Override
    public List<Game> getOrderCart() {
        return orderCart;
    }

    public UserServiceImpl(ValidationUtil validationUtil, UserRepository userRepository, GameRepository gameRepository, ModelMapper mapper) {
        this.validationUtil = validationUtil;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.mapper = mapper;

    }

    @Override
    public void registerUser(RegisterUserDto registerUserDto) {
        Set<ConstraintViolation<RegisterUserDto>> violation = validationUtil.violation(registerUserDto);
        if (!violation.isEmpty()) {
            violation.forEach(s -> System.out.println(s.getMessage()));
            return;
        }
        if (!registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
            System.out.println("Passwords must match.");
            return;
        }

        User user = mapper.map(registerUserDto, User.class);
        if (userRepository.count() == 0) {
            user.setAdmin(true);
        }
        userRepository.save(user);
    }

    @Override
    public void login(LoginUserDto loginUserDto) {
        if (this.loggedIn != null) {
            System.out.println("Already logged in.");
            return;
        }
        User user = userRepository.findByAndEmailAndPassword(loginUserDto.getEmail(), loginUserDto.getPassword());
        if (user != null) {
            this.loggedIn = user;
            orderCart = new ArrayList<>();
        } else {
            System.out.println("Incorrect username / password.");
        }
    }

    @Override
    public void logout() {
        if (loggedIn == null) {
            System.out.println("Cannot log out. No user was logged in.");
            return;
        }
        loggedIn = null;
    }

    @Override
    public List<GameDetailsDto> getAllMyGames() {
        List<GameDetailsDto> games = new ArrayList<>();
        if (loggedIn == null) {
            System.out.println("Please login.");
            return games;
        }
        loggedIn.getGames().forEach(s -> games.add(mapper.map(s, GameDetailsDto.class)));

        return games;
    }




}
