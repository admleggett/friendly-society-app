package com.friendly.passbook.service.impl;

import com.friendly.passbook.dto.AccountDto;
import com.friendly.passbook.entity.Account;
import com.friendly.passbook.repository.AccountRepository;
import com.friendly.passbook.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public AccountDto getAccountBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        return new AccountDto(account.getAccountNumber(), account.getOwnerName(), account.getBalance());
    }
}
