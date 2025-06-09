package com.friendly.passbook.dto;

import java.math.BigDecimal;

public record AccountDto(String accountNumber, String ownerName, BigDecimal balance) {
    public AccountDto {
        if (accountNumber == null || accountNumber.isBlank()) throw new IllegalArgumentException("Account number is required");
        if (ownerName == null || ownerName.isBlank()) throw new IllegalArgumentException("Owner name is required");
        if (balance == null) throw new IllegalArgumentException("Balance is required");
    }
}
