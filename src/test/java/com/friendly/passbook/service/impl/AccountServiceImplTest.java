package com.friendly.passbook.service.impl;

import com.friendly.passbook.dto.AccountDto;
import com.friendly.passbook.entity.Account;
import com.friendly.passbook.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    // Tests will be added here
    @Test
    void getAccountBalance_whenAccountExists_thenReturnAccountDto() {
        // Arrange
        String accountNumber = "1234567890";
        String ownerName = "John Doe";
        BigDecimal balance = new BigDecimal("1000.00");
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setOwnerName(ownerName);
        account.setBalance(balance);

        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(account);

        // Act
        AccountDto accountDto = accountService.getAccountBalance(accountNumber);

        // Assert
        assertEquals(accountNumber, accountDto.accountNumber());
        assertEquals(ownerName, accountDto.ownerName());
        assertEquals(balance, accountDto.balance());
    }

    @Test
    void getAccountBalance_whenAccountDoesNotExist_thenThrowIllegalArgumentException() {
        // Arrange
        String accountNumber = "0987654321";
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            accountService.getAccountBalance(accountNumber);
        });
        assertEquals("Account not found", exception.getMessage());
    }
}
