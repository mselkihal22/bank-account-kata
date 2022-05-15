package com.katas.bankaccountkata;

import com.katas.bankaccountkata.domain.Account;
import com.katas.bankaccountkata.domain.Client;
import com.katas.bankaccountkata.service.BankService;
import com.katas.bankaccountkata.service.BankServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

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
        bankService.depositToAccount(account, amount);
        assertThat((amount)).isEqualTo(account.getBalance());
    }

    @Test
    public void itShouldNotMakeDepositIntoBankAccountWhenAccountIsNull() {
        BigDecimal amount = new BigDecimal(-500);
        bankService.depositToAccount(null, amount);
        assertThat((amount)).isEqualTo(account.getBalance());
    }
}
