package com.katas.bankaccountkata.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String id;
    private Client client;
    private BigDecimal balance;
    private List<AccountTransaction> transactions;
}
