package com.friendly.passbook.service;

import com.friendly.passbook.dto.AccountDto;

public interface AccountService {
    AccountDto getAccountBalance(String accountNumber);
}
