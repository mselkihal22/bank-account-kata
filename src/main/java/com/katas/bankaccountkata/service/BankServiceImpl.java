package com.katas.bankaccountkata.service;

import com.katas.bankaccountkata.domain.Account;
import com.katas.bankaccountkata.domain.AccountTransaction;
import com.katas.bankaccountkata.domain.AccountTransactionType;
import com.katas.bankaccountkata.exceptions.OperationException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BankServiceImpl implements BankService {


    @Override
    public void depositToAccount(Account account, BigDecimal amount) {
        if (account == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new OperationException("cannot deposit to the account");
        }
        account.setBalance(account.getBalance().add(amount));
        addTransaction(account, amount, AccountTransactionType.DEPOSIT);
    }

    @Override
    public void withdrawFromAccount(Account account, BigDecimal amount) {
        if (account == null) {
            throw new IllegalArgumentException("Account must not be null");
        }
        if (amount.compareTo(account.getBalance()) > 0 || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new OperationException("cannot withdraw amount: " + amount + " from account: "+account.getId());
        }
        account.setBalance(account.getBalance().subtract(amount));
        addTransaction(account, amount, AccountTransactionType.WITHDRAWAL);
    }

    private void addTransaction(Account account, BigDecimal amount, AccountTransactionType operationType) {
        List<AccountTransaction> accountTransactions = account.getTransactions();
        accountTransactions.add(
                AccountTransaction.builder()
                        .accountTransactionType(operationType)
                        .amount(amount)
                        .balance(account.getBalance())
                        .date(LocalDateTime.now()).build());
    }


}
