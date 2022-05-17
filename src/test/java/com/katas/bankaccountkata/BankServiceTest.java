package com.katas.bankaccountkata;

import com.katas.bankaccountkata.domain.Account;
import com.katas.bankaccountkata.domain.AccountTransactionType;
import com.katas.bankaccountkata.domain.Client;
import com.katas.bankaccountkata.exceptions.OperationException;
import com.katas.bankaccountkata.service.BankService;
import com.katas.bankaccountkata.service.BankServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.katas.bankaccountkata.constatns.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class BankServiceTest {

    private BankService bankService;
    private Account account;

    @Before
    public void setup() {
        bankService = new BankServiceImpl();
        account = account.builder()
                .transactions(new ArrayList<>())
                .client(new Client())
                .balance(BigDecimal.ZERO)
                .build();
    }

    @Test
    public void itShouldMakeDepositIntoBankAccount() {
        BigDecimal amount = new BigDecimal(4000);
        bankService.depositToAccount(account, amount);
        assertThat((amount)).isEqualTo(account.getBalance());
    }

    @Test
    public void itShouldNotMakeDepositIntoBankAccountWhenAmountIsNegative() {
        BigDecimal amount = new BigDecimal(-600);
        try {
            bankService.depositToAccount(account, amount);
            fail("Exception should be thrown for negative Amount");
        } catch (OperationException exception) {
            assert (exception.getMessage().contains("cannot deposit to the account"));
        }
    }

    @Test
    public void itShouldNotMakeDepositIntoBankAccountWhenAccountIsNull() {
        BigDecimal amount = new BigDecimal(-500);
        try {
            bankService.depositToAccount(account, amount);
            fail("Exception should be thrown for negative Amount");
        } catch (OperationException exception) {
            assert (exception.getMessage().contains("cannot deposit to the account"));
        }
    }

    @Test
    public void itShouldMakeWithdrawalfromBankAccount() {
        BigDecimal accountBalance = new BigDecimal(1000);
        BigDecimal amount = new BigDecimal(50);
        account.setBalance(accountBalance);
        bankService.withdrawFromAccount(account, amount);
        assertThat(accountBalance.subtract(amount)).isEqualTo(account.getBalance());
    }

    @Test
    public void itShouldNotMakeWithdrawalWhenAmountIsInvalid() {
        BigDecimal accountBalance = new BigDecimal(100);
        account.setBalance(accountBalance);
        BigDecimal amount = new BigDecimal(500);
        try {
            bankService.withdrawFromAccount(account, amount);
            fail("Exception should be thrown when amount is greater than account balance");
        } catch (OperationException exception) {
            assert (exception.getMessage().contains("cannot withdraw amount"));
        }
    }

    @Test
    public void itShouldNotMakeWithdrawalWhenAccountWhenAccountIsNull() {
        BigDecimal accountBalance = new BigDecimal(1000);
        account.setBalance(accountBalance);
        BigDecimal amount = new BigDecimal(500);
        try {
            bankService.withdrawFromAccount(null, amount);
            fail("Exception should be thrown when account is null");
        } catch (IllegalArgumentException exception) {
            assert (exception.getMessage().contains("Account must not be null"));
        }
    }

    @Test
    public void itShouldPrintAccountStatementHistory() {
        bankService.depositToAccount(account, new BigDecimal(1000));
        bankService.withdrawFromAccount(account, new BigDecimal(350));
        bankService.withdrawFromAccount(account, new BigDecimal(250));
        StringBuilder ExpectedStatementHistory = new StringBuilder();
        account.getTransactions().forEach(accountTransaction ->
                ExpectedStatementHistory.append(OPERATION)
                        .append(accountTransaction.getAccountTransactionType())                  
                        .append(SEPARATOR)
                        .append(AMOUNT)
                        .append(accountTransaction.getAccountTransactionType().equals(AccountTransactionType.WITHDRAWAL) ?
                                accountTransaction.getAmount().negate().toString()
                                : accountTransaction.getAmount().toString())
                        .append(SEPARATOR)
                        .append(BALANCE)
                        .append(accountTransaction.getBalance())
                        .append(SEPARATOR)
                        .append(DATE)
                        .append(accountTransaction.getDate().format(DATE_FORMATTER))
                        .append(LINE_SEPARATOR));

        assertThat(bankService.accountStatementsHistory(account)).isEqualTo(ExpectedStatementHistory.toString());
    }

}
