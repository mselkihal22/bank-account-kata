package com.katas.bankaccountkata;

import com.katas.bankaccountkata.domain.Account;
import com.katas.bankaccountkata.util.AccountStatementPrinter;
import com.katas.bankaccountkata.domain.Client;
import com.katas.bankaccountkata.service.BankServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootApplication
public class BankaccountkataApplication {

    public static void main(String[] args) {
        BankServiceImpl bankService = new BankServiceImpl();
        AccountStatementPrinter accountStatementPrinter = new AccountStatementPrinter();
        Account account = new Account();
        account=account.builder()
                .transactions(new ArrayList<>())
                .balance(BigDecimal.ZERO)
                .client(new Client())
                .build();

        bankService.depositToAccount(account, new BigDecimal(500));
        bankService.depositToAccount(account, new BigDecimal(500));
        bankService.withdrawFromAccount(account, new BigDecimal(50));
        bankService.withdrawFromAccount(account, new BigDecimal(150));


        System.out.println(accountStatementPrinter.printAccountStatementsHistory(account));
    }

}
