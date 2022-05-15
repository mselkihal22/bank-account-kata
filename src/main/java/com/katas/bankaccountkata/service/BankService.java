package com.katas.bankaccountkata.service;

import com.katas.bankaccountkata.domain.Account;

import java.math.BigDecimal;

public interface BankService {

    void depositToAccount(Account account, BigDecimal amount);
    void withdrawFromAccount(Account account, BigDecimal amount);
}
