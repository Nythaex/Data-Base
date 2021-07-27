package com.example.work.services.interfaces;

import com.example.work.exeptions.NotEnoughMoneyException;
import com.example.work.exeptions.NotAccountUserException;

import java.math.BigDecimal;

public interface AccountService {

    void moneyWithdraw(Long account, BigDecimal money) throws NotEnoughMoneyException, NotAccountUserException;
    void moneyTransfer (Long transferAccount,Long getMoneyAccount ,BigDecimal money) throws NotAccountUserException;


}
