package com.example.work;

import com.example.work.exeptions.NotAccountUserException;
import com.example.work.exeptions.UsernameExistsExeption;
import com.example.work.services.interfaces.AccountService;
import com.example.work.services.interfaces.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RunApplication implements ApplicationRunner {
    private final UserService userService;
    private final AccountService accountService;

    public RunApplication(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {




        try {
            accountService.moneyTransfer(2L,13L, BigDecimal.valueOf(1));
        } catch (NotAccountUserException e) {
            e.printStackTrace();
        }


    }
}
