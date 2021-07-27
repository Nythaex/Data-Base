package com.example.work.services.Impl;

import com.example.work.exeptions.NotAccountUserException;
import com.example.work.exeptions.NotEnoughMoneyException;
import com.example.work.models.Account;
import com.example.work.models.User;
import com.example.work.repositories.AccountRepository;
import com.example.work.services.interfaces.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
           private final  AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void moneyWithdraw(Long accountId, BigDecimal money) throws NotAccountUserException {
          Account account=accountRepository.findById(accountId).orElse(null);
          if (account!=null){
              User user=account.getUser();
              if (user==null){
                  throw new NotAccountUserException();
              }
          }else {
              throw new NotAccountUserException();
          }

        int checker = account.getBalance().compareTo(money);
        if (checker >= 1) {
            account.setBalance(account.getBalance().subtract(money));
            accountRepository.save(account);
        } else {
            throw new NotEnoughMoneyException();
        }

    }

    private void moneyDeposit(Long accountId, BigDecimal money) {
        Account account=accountRepository.findById(accountId).orElse(null);
        account.setBalance(account.getBalance().add(money));
    }


    @Override
    public void moneyTransfer(Long transferAccountId, Long moneyDepositAccountId, BigDecimal money) throws NotAccountUserException {
        Account transferAccount=accountRepository.findById(transferAccountId).orElse(null);
        Account getMoneyAccount=accountRepository.findById(moneyDepositAccountId).orElse(null);
        if (transferAccount!=null&&getMoneyAccount!=null){
            User userOne=transferAccount.getUser();
            User userTwo=getMoneyAccount.getUser();
            if (userOne==null||userTwo==null){
                throw new NotAccountUserException();
            }
        }else {
            throw new NotAccountUserException();
        }

         moneyWithdraw(transferAccountId, money);
         moneyDeposit(moneyDepositAccountId,money);
    }
}
