package com.katas.bankaccountkata.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.katas.bankaccountkata.constatns.Constants.*;

@EqualsAndHashCode
@Getter
@Setter
@Builder
public class AccountTransaction {
    private BigDecimal amount;
    private BigDecimal balance;
    private AccountTransactionType accountTransactionType;
    private LocalDateTime date;

    @Override
    public String toString() {
        return new StringBuilder()
                .append(AMOUNT)
                .append(getAmount().toString())
                .append(SEPARATOR)
                .append(BALANCE)
                .append(getBalance())
                .append(SEPARATOR)
                .append(DATE)
                .append(getDate().format(DATE_FORMATTER))
                .append(LINE_SEPARATOR)
                .toString();
    }
}
