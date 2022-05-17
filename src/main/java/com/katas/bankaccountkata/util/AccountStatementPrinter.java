package com.katas.bankaccountkata.util;

import com.katas.bankaccountkata.domain.Account;

public class AccountStatementPrinter {
    private static StringBuilder accountHistory;

    public static String printAccountStatementsHistory(Account account) {
        accountHistory = new StringBuilder();
        account.getTransactions()
                .forEach(accountTransaction ->
                        accountHistory.append(accountTransaction.toString())
                );
        return accountHistory.toString();
    }
}
