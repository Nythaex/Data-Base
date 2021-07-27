package com.example.work.services.Impl;

import com.example.work.exeptions.UsernameExistsExeption;
import com.example.work.models.Account;
import com.example.work.models.User;
import com.example.work.repositories.AccountRepository;
import com.example.work.repositories.UserRepository;
import com.example.work.services.interfaces.AccountService;
import com.example.work.services.interfaces.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }



    @Override
    @Transactional
    public void registration(String username,int age, BigDecimal money) throws UsernameExistsExeption {
        if (userRepository.existsByUsername(username)){
            throw new UsernameExistsExeption("UsernameExists");
        }
         var user=new User(username,age);
         userRepository.save(user);
        Account account=new Account(money,user);
        accountRepository.save(account);
        user.getAccounts().add(account);



    }
}
