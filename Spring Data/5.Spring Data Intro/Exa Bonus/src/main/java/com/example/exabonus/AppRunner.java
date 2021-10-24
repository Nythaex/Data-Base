package com.example.exabonus;

import com.example.exabonus.models.service.UserServiceModel;
import com.example.exabonus.services.UsersService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppRunner implements ApplicationRunner {
   private UsersService userService;

    public AppRunner(UsersService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserServiceModel userServiceModel=new UserServiceModel();
        userServiceModel.setAge(12).setEmail("--123@gmail.com").setPassword("1234");
        userService.addUser(userServiceModel);

        String allEmail = userService.findAllEmail("info@softuni-bulgaria.org");
        System.out.println(allEmail);


    }
}
